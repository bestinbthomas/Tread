package com.treadhill.app.views


import android.content.Context
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.treadhill.app.R
import com.treadhill.app.factory_and_ingection.InjectionUtils
import com.treadhill.app.highOrder.*
import com.treadhill.app.utilities.WorkoutRecorder
import com.treadhill.app.viewModel.Actions
import com.treadhill.app.viewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_custom_workout.view.*
import java.util.*


class CustomWorkoutFragment : Fragment() {

    private var totalHR: Int = 0
    private var count: Int = 0
    private var pauseOffset: Long = 0
    private lateinit var viewModel: MainViewModel
    private lateinit var mView: View
    private var isTimerRunning = false
    private val avgHeartRate: Int
        get() = if (count != 0)
            (totalHR / count)
        else 0
    private var heartRate = 0
    private val time: Float
        get() {
            if (isTimerRunning)
                return (SystemClock.elapsedRealtime() - mView.timer.base).toFloat() / 3600000f
            else return pauseOffset.toFloat() / 3600000f
        }

    private lateinit var workoutRecorder: WorkoutRecorder
    private var workoutId = "CustomWorkout"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_custom_workout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(
            requireActivity(),
            InjectionUtils.provideMainViewModelFactory(requireActivity().applicationContext)
        ).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mView = view
    }

    override fun onStart() {
        super.onStart()

        workoutId += " : " + Calendar.getInstance().timeInMillis.toString()

        val preferences = requireActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val dob = preferences.getLong(PREF_DOB, 0)

        val dobCal = Calendar.getInstance()
        dobCal.timeInMillis = dob

        val age = Calendar.getInstance()[Calendar.YEAR] - dobCal[Calendar.YEAR]

        workoutRecorder = WorkoutRecorder(age, preferences.getInt(PREF_WEIGHT, 50), preferences.getString(PREF_GENDER, "M")!!)

        mView.timer_start_pause.setOnClickListener { startStopTimer() }
        mView.timer_reset.setOnClickListener { resetTimer() }
        mView.timer_stop.setOnClickListener { stopWorkout() }

        setObservations()
        setupSlider()
    }

    private fun stopWorkout() {
        if (workoutRecorder.startTime != 0.toLong()) {
            val workoutStats = workoutRecorder.finishWorkout(SystemClock.elapsedRealtime())
            val maxHeartRate = workoutRecorder.maxHeartRate
            val avgHR = workoutRecorder.avgHR
            Log.i("WORKOUT", " finished max : $maxHeartRate avg : $avgHR stats $workoutStats")

            val workoutSummary = workoutRecorder.getWorkoutSummary(workoutId)
            viewModel.listen(Actions.PostWorkoutSummary(workoutSummary))
            val preferences = requireActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            val dob = preferences.getLong(PREF_DOB, 0)

            val dobCal = Calendar.getInstance()
            dobCal.timeInMillis = dob

            val age = Calendar.getInstance()[Calendar.YEAR] - dobCal[Calendar.YEAR]
            workoutRecorder = WorkoutRecorder(age, preferences.getInt(PREF_WEIGHT, 50), preferences.getString(PREF_GENDER, "M")!!)
            findNavController().navigate(
                CustomWorkoutFragmentDirections.actionCustomWorkoutFragmentToWorkoutSummaryFragment(workoutSummary)
            )
        } else showSnackbar("You did not start workout", Snackbar.LENGTH_SHORT)

    }

    private fun setupSlider() {
        mView.heart_slider.disableBulge()
    }

    private fun setObservations() {
        viewModel.heartRate.observe(this,
            Observer {
                setHeartRate(it)
            })
    }

    private fun setHeartRate(heartRate: String) {
        mView.heart_rate.text = heartRate
        mView.heart_slider.heartRate = heartRate.toFloat()
        this.heartRate = heartRate.toInt()
        mView.max_heart_rate.text = workoutRecorder.maxHeartRate.toString()
        mView.avg_heart_rate.text = avgHeartRate.toString()
        // TODO: change to to percent fun
        mView.percent_heart_rate.text = (avgHeartRate / 2).toString() + "%"
        mView.percent_progress.progress = (avgHeartRate / 2)
        if (isTimerRunning) {
            totalHR += heartRate.toInt()
            count++
            Log.i("WORKOUT", "avgHeartRate $avgHeartRate")
            mView.calories.text = workoutRecorder.getCaloriesBurned().toString()
            viewModel.listen(Actions.PostHeartRate(workoutId, heartRate.toInt()))
            if (workoutRecorder.checkZoneChange(this.heartRate))
                workoutRecorder.onZoneChange(heartRate.toInt(), SystemClock.elapsedRealtime())
        }
    }

    private fun startStopTimer() {
        if (!isTimerRunning) {
            if (pauseOffset == 0.toLong())
                workoutRecorder.startWorkout(SystemClock.elapsedRealtime(), heartRate)
            else
                workoutRecorder.resumeWorkout(SystemClock.elapsedRealtime(), heartRate)
            workoutRecorder.checkZoneChange(heartRate)
            mView.timer.base = SystemClock.elapsedRealtime() - pauseOffset
            mView.timer.start()
            isTimerRunning = true
            mView.timer_start_pause.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_pause_player,
                0,
                0,
                0
            )
            mView.timer_start_pause.text = resources.getString(R.string.pause)
        } else {
            mView.timer.stop()
            workoutRecorder.pauseWorkout(SystemClock.elapsedRealtime())
            pauseOffset = SystemClock.elapsedRealtime() - mView.timer.base
            isTimerRunning = false
            mView.timer_start_pause.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_play_player,
                0,
                0,
                0
            )
            mView.timer_start_pause.text = resources.getString(R.string.resume)
        }
    }

    private fun resetTimer() {
        mView.timer.base = SystemClock.elapsedRealtime()
        pauseOffset = 0
        if (!isTimerRunning) mView.timer_start_pause.text = resources.getString(R.string.start)
    }


}

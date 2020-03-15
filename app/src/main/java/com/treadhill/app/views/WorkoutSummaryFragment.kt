package com.treadhill.app.views


import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.formatter.PercentFormatter
import com.treadhill.app.R
import com.treadhill.app.factory_and_ingection.InjectionUtils
import com.treadhill.app.viewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_workout_summary.view.*


class WorkoutSummaryFragment : Fragment() {


    private lateinit var viewModel: MainViewModel
    private lateinit var mView: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workout_summary, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mView = view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(
            requireActivity(),
            InjectionUtils.provideMainViewModelFactory(requireActivity().applicationContext)
        ).get(MainViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()

        val workoutSummary = navArgs<WorkoutSummaryFragmentArgs>().value.workoutSummary
        val averageHeartRate = workoutSummary.avgHeartRate
        val zoneData = workoutSummary.zoneInfo
        val totalTime = workoutSummary.totalTime
        Log.i(
            "WORKOUT SUMMARY",
            "avg : $averageHeartRate total time : $totalTime zoneData : $zoneData"
        )
//        val hour = totalTime/3600000
//        val min = (totalTime%3600000)/60
//        val sec = ((totalTime%3600000)%60)/60
//        if(hour.toInt() != 0){
//            mView.time.text = "$hour:$min:$sec"
//            mView.time_unit.visibility = View.INVISIBLE
//        }else mView.time.text = "$min:$sec"
        mView.time.text = (totalTime / 1000).toString()
        mView.max_heart_rate.text =
            workoutSummary.maxHeartRate.toString()
        mView.calories.text = workoutSummary.calories.toString()
        mView.average_heart_rate.text = averageHeartRate.toString()
        setupPieChart(workoutSummary.zoneInfo)
    }

    private fun setupPieChart(zoneData: List<Int>) {
        val entries = ArrayList<Entry>()
        val labels = ArrayList<String>()
        zoneData.forEachIndexed { index, value ->
            if(value > 0) {
                entries.add(Entry(value.toFloat(), index))
                labels.add("${index * 10}% to ${(index + 1) * 10}%")
                Log.i("SUMMARY", "${index * 10}% to ${(index + 1) * 10}% : $value")
            }

        }
        val pieDataSet = PieDataSet(entries, "")
        pieDataSet.colors = arrayListOf(
            Color.parseColor("#c9cacc"),
            Color.parseColor("#58a6f9"),
            Color.parseColor("#42e89e"),
            Color.parseColor("#fca00d"),
            Color.parseColor("#e6ca24"),
            Color.parseColor("#e6243d")
        )
        pieDataSet.sliceSpace = 5f
        pieDataSet.valueFormatter = PercentFormatter()
        pieDataSet.valueTextColor = Color.BLACK
        pieDataSet.valueTextSize = 20f
        val pieData = PieData(labels, pieDataSet)
//        pieData.setValueTextColor(Color.Y)
//        pieData.setValueTextSize(18f)
        mView.summary_chart.data = pieData
        mView.summary_chart.isDrawHoleEnabled = true
        mView.summary_chart.holeRadius = 30f
        mView.summary_chart.transparentCircleRadius = 45f

        mView.summary_chart.setUsePercentValues(true)
        mView.summary_chart.dragDecelerationFrictionCoef = 0.99f
        //mView.summary_chart.setExtraOffsets(5f,10f,5f,10f)
        mView.summary_chart.animateY(1000)
        mView.summary_chart.legend.isEnabled = false
    }


}

package com.treadhill.app.views

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.treadhill.app.R
import com.treadhill.app.adapters.SummaryRecyclerAdapter
import com.treadhill.app.dataTypes.WeakInfo
import com.treadhill.app.factory_and_ingection.InjectionUtils
import com.treadhill.app.highOrder.copy
import com.treadhill.app.highOrder.getDateInFormat
import com.treadhill.app.viewModel.Actions
import com.treadhill.app.viewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_dashboard.view.*
import java.util.*
import kotlin.collections.ArrayList

class DashboardFragment : Fragment() {

    companion object {
        fun newInstance() = DashboardFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var mView: View
    lateinit var adapter: SummaryRecyclerAdapter
    private var weakInfo = WeakInfo()
    private var spinerPosition = 0
    private val date = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity(), InjectionUtils.provideMainViewModelFactory(requireActivity().applicationContext)).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mView = view
    }

    override fun onStart() {
        super.onStart()

        checkSpinner()
        setupRecycler()
        setObservations()
        setOnClicks()
        setDate()
        viewModel.listen(Actions.GetWeekInfo(date))
    }

    /**
     * sets date below graph
     *
     */
    private fun setDate() {
        val startOfWeak = date.copy()
        startOfWeak.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
        val endOfWeak = date.copy()
        endOfWeak.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY)
        mView.dash_date.text = "${startOfWeak.getDateInFormat()} - ${endOfWeak.getDateInFormat()}"
    }

    /**
     * handle next and previous week button clicks
     *
     */
    private fun setOnClicks() {
        mView.next_button.setOnClickListener {
            date.add(Calendar.DATE, 7)
            viewModel.listen(Actions.GetWeekInfo(date))
            setDate()
        }
        mView.prev_button.setOnClickListener {
            date.add(Calendar.DATE, -7)
            viewModel.listen(Actions.GetWeekInfo(date))
            setDate()
        }
    }

    /**
     * Presents the WeakMetadata in graph using MPAndroidChart
     *
     * @param weakInfo
     */
    private fun setupGraph(weakInfo: WeakInfo) {
        val enteries = ArrayList<BarEntry>()

        val data: FloatArray = when (spinerPosition) {
            0 -> {
                val scoreArray = FloatArray(7) { 0f }
                weakInfo.scores.forEachIndexed { index, l -> scoreArray[index] = l.toFloat() }
                scoreArray
            }
            1 -> {
                val durationArray = FloatArray(7) { 0f }
                weakInfo.duration.forEachIndexed { index, l -> durationArray[index] = if (l == 0.toLong()) 0f else l.toFloat() / (1000 * 60) }
                durationArray
            }
            2 -> {
                val calorieArray = FloatArray(7) { 0f }
                weakInfo.calories.forEachIndexed { index, l -> calorieArray[index] = l.toFloat() }
                calorieArray
            }
            else -> FloatArray(7)
        }

        Log.i("DASHBOARD", "bargraph data ${data}")

        for (i in data.indices) {
            enteries.add(BarEntry(data[i].toFloat(), i))
        }
        val labels = ArrayList<String>()
        labels.add("Sun")
        labels.add("Mon")
        labels.add("Tue")
        labels.add("Wed")
        labels.add("Thu")
        labels.add("Fri")
        labels.add("Sat")
        val barDataSet = BarDataSet(enteries, "Scores")
        barDataSet.colors = arrayListOf(Color.parseColor("#c9cacc"),
            Color.parseColor("#58a6f9"),
            Color.parseColor("#42e89e"),
            Color.parseColor("#fca00d"),
            Color.parseColor("#e6ca24"),
            Color.parseColor("#e6243d"))
        barDataSet.valueTextColor = Color.BLACK
        barDataSet.valueTextSize = 20f
        val barData = BarData(labels, barDataSet)
//        pieData.setValueTextColor(Color.Y)
//        pieData.setValueTextSize(18f)
        mView.barChart.data = barData

        mView.barChart.dragDecelerationFrictionCoef = 0.99f
        //mView.barChart.setExtraOffsets(5f,10f,5f,10f)
        mView.barChart.animateY(1000)
        mView.barChart.legend.isEnabled = false
    }

    /**
     * attaches adapter to the list of video recycler view
     *
     */
    private fun setupRecycler() {
        adapter = SummaryRecyclerAdapter(requireContext())
        mView.video_list.adapter = adapter
        adapter.clickListener.observe(this,
            Observer {
                findNavController().navigate(DashboardFragmentDirections.actionDashboardFragmentToWorkoutSummaryFragment(adapter.currentList[it]))
            })
    }

    /**
     * Observes Weak metadata and List of summaries from viewmodel
     *
     */
    private fun setObservations() {
        viewModel.weakInfo.observe(this,
            Observer {
                Log.i("DASHBOARD", "weakinfo recieved ${it?.scores ?: ""} : ${it?.duration ?: ""} : ${it?.calories ?: ""}")
                weakInfo = it ?: WeakInfo()
                setupGraph(weakInfo)
            })

        viewModel.weakWorkoutSummaries.observe(this,
            Observer {
                Log.i("DASHBOARD", "workouts recieved ${it?.size ?: ""} : ${if (it?.isNotEmpty() == true) it[it.size - 1].workoutId else ""}")
                adapter.submitList(it ?: listOf())
            })
    }

    /**
     * handles different options selected by the spinner - scores, calories and duration
     *
     */
    private fun checkSpinner() {
        mView.option_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                parent?.setSelection(0)
                spinerPosition = 0
                setupGraph(weakInfo)
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                spinerPosition = position
                setupGraph(weakInfo)
            }

        }
    }
}

package com.treadhill.app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.treadhill.app.R
import com.treadhill.app.dataTypes.WorkoutSummary
import com.treadhill.app.highOrder.getDateTimeInFormat
import java.util.*

class SummaryRecyclerAdapter(val context: Context) : ListAdapter<WorkoutSummary, SummaryRecyclerAdapter.SummaryHolder>(object : DiffUtil.ItemCallback<WorkoutSummary>() {
    override fun areItemsTheSame(oldItem: WorkoutSummary, newItem: WorkoutSummary): Boolean =
        oldItem.workoutId == newItem.workoutId && oldItem.timeStamp == newItem.timeStamp


    override fun areContentsTheSame(oldItem: WorkoutSummary, newItem: WorkoutSummary): Boolean =
        oldItem.workoutId == newItem.workoutId

}) {

    val clickListener = MutableLiveData<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SummaryHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.summary_rec_item, parent, false)
        return SummaryHolder(view)
    }

    override fun onBindViewHolder(holder: SummaryHolder, position: Int) {
        holder.name.text = getItem(position).workoutId.split(":", ignoreCase = false)[0]
        holder.calories.text = context.resources.getString(R.string.summary_calories, getItem(position).calories.toString())
        holder.score.text = context.resources.getString(R.string.summary_score, getItem(position).totalScore.toString())
        holder.duration.text = context.resources.getString(R.string.summary_duration, (getItem(position).totalTime / (1000 * 60)).toString())
        val date = Calendar.getInstance()
        date.timeInMillis = getItem(position).timeStamp
        holder.date.text = date.getDateTimeInFormat()
        holder.view.setOnClickListener {
            clickListener.value = position
        }
    }

    class SummaryHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.workout_name)
        val score = view.findViewById<TextView>(R.id.workout_score)
        val duration = view.findViewById<TextView>(R.id.workout_duration)
        val calories = view.findViewById<TextView>(R.id.workout_calories)
        val date = view.findViewById<TextView>(R.id.date)
    }
}
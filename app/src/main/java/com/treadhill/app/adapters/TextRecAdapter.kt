package com.treadhill.app.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.treadhill.app.R

class TextRecAdapter(val texts: List<String>, val context: Context) : RecyclerView.Adapter<TextRecAdapter.Holder>() {
    var selectedItems = arrayListOf<Int>()
        private set

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val textView = LayoutInflater.from(parent.context).inflate(R.layout.test_view_layout, parent, false)
        return Holder(textView)
    }

    override fun getItemCount(): Int =
        texts.size


    override fun onBindViewHolder(holder: Holder, position: Int) {
        (holder.view as TextView).text = texts[position]
        if (position in selectedItems) {
            holder.view.background = context.getDrawable(R.drawable.cal_accent_grey)
        } else holder.view.background = context.getDrawable(R.drawable.round_border_back)
        holder.view.setOnClickListener {
            if (position in selectedItems) selectedItems.remove(position)
            else selectedItems.add(position)
            selectedItems.forEach {
                Log.i("selected", it.toString())
            }
            notifyItemChanged(position)
        }
    }

    fun setSelectedList(list: ArrayList<Int>) {
        selectedItems = list
        notifyDataSetChanged()
    }

    class Holder(val view: View) : RecyclerView.ViewHolder(view)
}
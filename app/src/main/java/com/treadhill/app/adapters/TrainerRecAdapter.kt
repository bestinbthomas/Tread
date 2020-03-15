package com.treadhill.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.treadhill.app.R
import com.treadhill.app.dataTypes.Trainer

class TrainerRecAdapter(val trainerList: ArrayList<Trainer>, val clickListener: MutableLiveData<Int> = MutableLiveData()) : RecyclerView.Adapter<TrainerRecAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.trainer_rec_item, parent, false))
    }

    override fun getItemCount(): Int = trainerList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.image.setImageResource(trainerList[position].resid)
        holder.view.setOnClickListener {
            clickListener.value = position
        }
    }

    class Holder(val view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.trainer_img)

    }
}

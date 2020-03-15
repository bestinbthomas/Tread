package com.treadhill.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.treadhill.app.R
import com.treadhill.app.dataTypes.VideoThumb

class HomeRecyclerAdapter(var videoThumbnails: ArrayList<VideoThumb>, val clickListner: MutableLiveData<Int> = MutableLiveData()) : RecyclerView.Adapter<HomeRecyclerAdapter.MyHolder>(), Filterable {

    private val fullVideoThumbnails: ArrayList<VideoThumb> = videoThumbnails.clone() as ArrayList<VideoThumb>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MyHolder(layoutInflater.inflate(R.layout.home_rec_item, parent, false))
    }

    override fun getItemCount(): Int {
        return videoThumbnails.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.name.text = videoThumbnails[position].name
        holder.desc.text = videoThumbnails[position].description
        holder.back.setImageResource(R.mipmap.img1)
        videoThumbnails[position].backUrl?.let {
            holder.back.load(it)
        }
        holder.view.setOnClickListener {
            clickListner.value = position
        }
    }

    class MyHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val name: TextView = view.findViewById(R.id.video_name)
        val desc: TextView = view.findViewById(R.id.video_desc)
        val back: ImageView = view.findViewById(R.id.video_back)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = ArrayList<VideoThumb>(fullVideoThumbnails)
                constraint?.let { querry ->
                    if (querry.isNotEmpty()) {
                        filteredList.filter { videoThumb ->
                            videoThumb.name.toLowerCase().contains(querry.toString().trim(), true)
                        }
                    }
                }
                val res = FilterResults()
                res.values = filteredList
                return res
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                videoThumbnails.clear()
                results?.let {
                    val vids: ArrayList<VideoThumb> = it.values as ArrayList<VideoThumb>
                    videoThumbnails.addAll(vids)
                    notifyDataSetChanged()
                }
            }

        }
    }
}
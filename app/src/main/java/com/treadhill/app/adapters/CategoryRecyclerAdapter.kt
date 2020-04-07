
package com.treadhill.app.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.treadhill.app.R
import com.treadhill.app.dataTypes.VideoItem

class CategoryRecyclerAdapter(val context: Context, private var videoItems: ArrayList<VideoItem>, val clickListner: MutableLiveData<Int> = MutableLiveData()) : RecyclerView.Adapter<CategoryRecyclerAdapter.MyHolder>() {

    init {
        Log.e("Category rec", "init called")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MyHolder(layoutInflater.inflate(R.layout.subcat_rec_item, parent, false))
    }

    override fun getItemCount(): Int {
        Log.e("Category Item", " no of items = ${videoItems.size}")
        return videoItems.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.name.text = videoItems[position].name
        holder.desc.text = videoItems[position].VideoDescription
        if (videoItems[position].ThumbnailUrl != null)
            holder.back.load(videoItems[position].ThumbnailUrl) {
                placeholder(R.mipmap.img1)
                transformations(CircleCropTransformation())
            }
        else holder.back.load(R.mipmap.img1) {
            transformations(CircleCropTransformation())
        }
        holder.rating.text = videoItems[position].rating.toString()
        holder.difficulty.text = videoItems[position].LevelOfDifficulty
        holder.duration.text = context.resources.getString(R.string.duration, videoItems[position].VideoLength)
        holder.view.setOnClickListener {
            clickListner.value = position
        }
    }

    fun submitList(videos: ArrayList<VideoItem>) {
        this.videoItems = videos
        notifyDataSetChanged()
    }

    class MyHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val name: TextView = view.findViewById(R.id.name)
        val desc: TextView = view.findViewById(R.id.description)
        val back: ImageView = view.findViewById(R.id.avatar)
        val rating: TextView = view.findViewById(R.id.rating)
        val duration: TextView = view.findViewById(R.id.duration)
        val difficulty: TextView = view.findViewById(R.id.difficulty)
    }

}

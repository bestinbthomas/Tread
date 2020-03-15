package com.treadhill.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.treadhill.app.R
import com.treadhill.app.dataTypes.LeaderboardItem

class LeaderBoardRecAdapter :
    ListAdapter<LeaderboardItem, LeaderBoardRecAdapter.LeaderBoardViewHolder>(object :
        DiffUtil.ItemCallback<LeaderboardItem>() {
        override fun areItemsTheSame(oldItem: LeaderboardItem, newItem: LeaderboardItem): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(
            oldItem: LeaderboardItem,
            newItem: LeaderboardItem
        ): Boolean =
            oldItem.avatar == newItem.avatar && oldItem.name == newItem.name && oldItem.score == newItem.score

    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderBoardViewHolder =
        LeaderBoardViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.leaderboard_rec_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: LeaderBoardViewHolder, position: Int) {
        val item = getItem(position)
        holder.avatar.setImageResource(item.avatar)
        holder.name.text = item.name
        holder.score.text = item.score.toString()
    }

    class LeaderBoardViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val avatar = view.findViewById<ImageView>(R.id.player_img)
        val name = view.findViewById<TextView>(R.id.Player_name)
        val score = view.findViewById<TextView>(R.id.Player_score)
    }

}

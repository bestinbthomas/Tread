package com.treadhill.app.dataTypes

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FilterOptions(var filterTypes: ArrayList<Int> = arrayListOf(),
                         var filterDifficulties: ArrayList<Int> = arrayListOf(),
                         var filterDuration: Int = 0,
                         var querry: String = "") : Parcelable {

    private fun checkQuerry(video: VideoItem): Boolean =
        video.name.contains(querry, false) || video.CreatorName?.contains(querry, false) ?: false || video.VideoCategory?.contains(querry, false) ?: false || video.Equipment?.contains(querry, false) ?: false


    private fun checkDuration(duration: Int): Boolean =
        if (filterDuration == 0) true
        else duration <= filterDuration


    private fun checkDifficulty(difficulty: String?): Boolean =
        if (filterDifficulties.isEmpty()) true
        else {
            when (difficulty) {
                // todo convert to enum
                "beginner" -> filterDifficulties.contains(0)
                "intermediate" -> filterDifficulties.contains(1)
                "difficult" -> filterDifficulties.contains(2)
                else -> false
            }
        }

    private fun checkType(category: String?): Boolean =
        if (filterTypes.isEmpty()) true
        else {
            when (category) {
                "hitt" -> filterTypes.contains(0)
                "pilates" -> filterTypes.contains(1)
                "zumba" -> filterTypes.contains(2)
                "yoga" -> filterTypes.contains(3)
                "combo" -> filterTypes.contains(4)
                else -> false
            }
        }

    fun filter(videos: List<VideoItem>?) = videos?.filter {
        checkDuration(it.VideoLength
            ?: 0) && checkDifficulty(it.LevelOfDifficulty?.toLowerCase()?.trim()) && checkType(it.VideoCategory?.toLowerCase()?.trim()) && checkQuerry(it)
    } ?: listOf()
}

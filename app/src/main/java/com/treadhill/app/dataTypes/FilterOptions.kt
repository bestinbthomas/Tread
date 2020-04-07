package com.treadhill.app.dataTypes

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Utility to Filter videos in Category Fragment
 *
 * @property filterTypes List of Types Selected ["hitt","zumba"] etc
 * @property filterDifficulties List of Difficulty level selected ["Beginer", "Intermediate"]
 * @property filterDuration Maximum Duration
 * @property query Search query or string entered by the user
 */
@Parcelize
data class FilterOptions(
    var filterTypes: ArrayList<Int> = arrayListOf(),
    var filterDifficulties: ArrayList<Int> = arrayListOf(),
    var filterDuration: Int = 0,
    var query: String = ""
) : Parcelable {

    /**
     * checks of the Video has the search query in its tittle, description, Creator, Category or Equipment
     *
     * @param video the video to check
     * @return true if the searach string exist or flase
     */
    private fun checkQuery(video: VideoItem): Boolean =
        video.name.contains(query, false) ||
                video.CreatorName?.contains(query, false) ?: false ||
                video.VideoCategory?.contains(query, false) ?: false ||
                video.Equipment?.contains(query, false) ?: false ||
                video.VideoDescription?.contains(query, false) ?: false


    /**
     * Check if the duration is less then givn duration
     *
     * @param duration duration of video
     * @return Boolean
     */
    private fun checkDuration(duration: Int): Boolean =
        if (filterDuration == 0) true
        else duration <= filterDuration


    /**
     * Checks if the given difficulty is in the List of Difficulties
     *
     * @param difficulty difficulty of video
     * @return
     */
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

    /**
     * Checks if the given category is in the List of Types
     *
     * @param category video category
     * @return
     */
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

    /**
     * Filter the list of videos according to the conditions passed in the constructor
     *
     * @param videos List of videos
     */
    fun filter(videos: List<VideoItem>?) = videos?.filter {
        checkDuration(it.VideoLength
            ?: 0) && checkDifficulty(it.LevelOfDifficulty?.toLowerCase()?.trim()) && checkType(it.VideoCategory?.toLowerCase()?.trim()) && checkQuery(it)
    } ?: listOf()
}

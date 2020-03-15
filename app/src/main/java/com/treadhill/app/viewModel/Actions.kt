package com.treadhill.app.viewModel

import com.treadhill.app.dataTypes.FilterOptions
import com.treadhill.app.dataTypes.WorkoutSummary
import java.util.*

sealed class Actions {
    data class SearchChange(val querry: String?, val submit: Boolean = false) : Actions()
    data class PostHeartRate(val workoutId: String, val value: Int) : Actions()
    data class BluetoothState(val state: Int) : Actions()
    data class HeartRate(val data: String) : Actions()
    object Logout : Actions()
    object ScanBluetooth : Actions()
    object Filter : Actions()
    data class FilterVideos(val options: FilterOptions) : Actions()
    data class PostWorkoutSummary(val workoutSummary: WorkoutSummary) : Actions()
    data class PostScore(val workoutId: String, val score: Int) : Actions()
    data class GetWorkoutsInWeak(val date: Calendar) : Actions()
    data class GetWeekInfo(val date: Calendar) : Actions()
    data class GetVimeoVideo(val uri: String) : Actions()
}
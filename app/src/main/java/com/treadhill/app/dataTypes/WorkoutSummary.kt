package com.treadhill.app.dataTypes

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WorkoutSummary(var workoutId: String = "",
                          var timeStamp: Long = 0,
                          var totalTime: Long = 0,
                          var avgHeartRate: Int = 0,
                          var maxHeartRate: Int = 0,
                          var calories: Int = 0,
                          var totalScore: Int = 0,
                          var zoneInfo: List<Int> = List(10) { 0 }
) : Parcelable {
}
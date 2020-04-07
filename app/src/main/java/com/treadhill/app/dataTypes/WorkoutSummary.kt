package com.treadhill.app.dataTypes

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Summary of each workout
 *
 * Stored in Firebase in the week collection
 *
 * @property workoutId Id of workout
 * @property timeStamp time in milliseconds of when workout was done
 * @property totalTime total time spent doing the workout
 * @property avgHeartRate average heart rate
 * @property maxHeartRate maximum heart rate reached durin gthe workout
 * @property calories total calories burnt
 * @property totalScore total score gained in the workout
 * @property zoneInfo List of time spent in each zone in milliseconds
 */
@Parcelize
data class WorkoutSummary(var workoutId: String = "",
                          var timeStamp: Long = 0,
                          var totalTime: Long = 0,
                          var avgHeartRate: Int = 0,
                          var maxHeartRate: Int = 0,
                          var calories: Int = 0,
                          var totalScore: Int = 0,
                          var zoneInfo: List<Int> = List(10) { 0 }
) : Parcelable
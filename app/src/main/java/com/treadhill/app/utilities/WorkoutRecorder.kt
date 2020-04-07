package com.treadhill.app.utilities

import android.os.SystemClock
import android.util.Log
import com.treadhill.app.dataTypes.WorkoutSummary
import com.treadhill.app.highOrder.findCalories
import java.util.*
import kotlin.collections.ArrayList

/**
 * recordes the Workout and generate Summary
 *
 * @property age
 * @property weight
 * @property gender
 * @property currZone *Dont pass anything and keep the default value
 * @property timeinZone *Dont pass anything and keep the default value
 */
class WorkoutRecorder(val age: Int, val weight: Int, val gender: String, var currZone: Int = 0, var timeinZone: IntArray = IntArray(10)) {
    var initStartTime: Long = SystemClock.elapsedRealtime()
        private set
    val totalTime: Long
        get() = (SystemClock.elapsedRealtime() - initStartTime)
    var startTime: Long = 0
    private var pauseTime: Long = 0
    private var totalHR: Long = 0
    private var countHR: Int = 0
    var didWorkoutStart: Boolean = false

    /**
     * calculate the get average Heart Rate
     */
    val avgHR: Int
        get() = if (countHR > 0) (totalHR / countHR).toInt() else 0
    var maxHeartRate: Int = 0
        private set


    var heartRatePercent: Int = 0
        /**
         * converts the given heart rate to percentage and stores
         */
        set(value) {
            field = (value * 100 / maxHR).toInt()
            Log.i("WORKOUT", "heartRatePercent $field")
        }
    val maxHR = 200

    /**
     * Pause the recording of workout
     *
     * @param time
     */
    fun pauseWorkout(time: Long) {
        pauseTime = time
        Log.d("WORKOUT REC", "pause workout")
    }

    var score = 0

    /**
     *
     *
     * @return Calories Burned
     */
    fun getCaloriesBurned(): Int {
        Log.e("CALORIES", "age : $age, weight: $weight, gender $gender, totatime : $totalTime ")
        return avgHR.findCalories(age, weight, gender, totalTime / 3600000f)
    }

    /**
     * resume recording of workout
     *
     * @param time time in millisecond when it is resumed
     * @param heartRate
     */
    fun resumeWorkout(time: Long, heartRate: Int) {
        heartRatePercent = heartRate
        initStartTime += (time - pauseTime)
        if (heartRatePercent / 10 == currZone) {
            startTime += (time - pauseTime)
            Log.d("WORKOUT REC", "resume workout same")
        } else {
            timeinZone[currZone] += (pauseTime - startTime).toInt()
            currZone = heartRatePercent / 10
            startTime = time
            Log.d("WORKOUT REC", "resume workout different")
        }
    }

    /**
     * Start the workout
     *
     * NOTE: this is called only once for a workout subsequently call resumeWorkout()
     *
     * @param time
     * @param heartRate
     */
    fun startWorkout(time: Long, heartRate: Int) {
        didWorkoutStart = true
        heartRatePercent = heartRate
        initStartTime = time
        startTime = time
        pauseTime = 0
        timeinZone = IntArray(10)
        currZone = heartRatePercent / 10
        Log.d("WORKOUT REC", "start workout")
    }

    /**
     * Check if the new Heart Rate is in a different zone than previous
     *
     * NOTE call it every second that is every time HR is received fom moniter
     *
     * @param heartRate
     * @return
     */
    fun checkZoneChange(heartRate: Int): Boolean {
        heartRatePercent = heartRate
        totalHR += heartRate
        countHR++
        if (heartRate > maxHeartRate) maxHeartRate = heartRate
        Log.d("WORKOUT REC", "checkZone change : ${heartRatePercent / 10 != currZone}")
        return heartRatePercent / 10 != currZone
    }

    /**
     * Increase the score by the inc provided
     *
     * @param inc
     */
    fun incrmentScore(inc: Int) {
        score += inc
    }

    /**
     * handle zone change - add the time in the previous zone and change the current zone
     *
     * @param heartRate
     * @param time in milliseconds
     */
    fun onZoneChange(heartRate: Int, time: Long) {
        heartRatePercent = heartRate
        Log.d("WORKOUT REC", "zone change from $currZone to ${heartRatePercent / 10}")
        timeinZone[currZone] += (time - startTime).toInt()
        currZone = heartRatePercent / 10
        startTime = time
    }

    /**
     * Finishes the workout
     *
     * @param time
     * @return Array of time in milliseconds spent in each zone
     */
    fun finishWorkout(time: Long): IntArray {
        timeinZone[currZone] += (time - startTime).toInt()
        Log.d("WORKOUT REC", "finish workout")
        return timeinZone
    }

    /**
     * Call after finishing the workout
     *
     * @param workoutId Id of the Workout to be saved in firebase
     * @return WorkoutSummary Object for the workout
     */
    fun getWorkoutSummary(workoutId: String): WorkoutSummary {
        val zonedata = ArrayList<Int>(10)
        zonedata.addAll(arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0))
        timeinZone.forEachIndexed { index, i ->
            zonedata[index] = i
        }
        return WorkoutSummary(workoutId, Calendar.getInstance().timeInMillis, totalTime, avgHR, maxHeartRate, getCaloriesBurned(), score, zonedata)
    }
}
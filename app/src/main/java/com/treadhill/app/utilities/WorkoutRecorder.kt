package com.treadhill.app.utilities

import android.os.SystemClock
import android.util.Log
import com.treadhill.app.dataTypes.WorkoutSummary
import com.treadhill.app.highOrder.findCalories
import java.util.*
import kotlin.collections.ArrayList

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
    val avgHR: Int
        get() = if (countHR > 0) (totalHR / countHR).toInt() else 0
    var maxHeartRate: Int = 0
        private set
    var heartRatePercent: Int = 0
        set(value) {
            field = (value * 100 / maxHR).toInt()
            Log.i("WORKOUT", "heartRatePercent $field")
        }
    val maxHR = 200
    fun pauseWorkout(time: Long) {
        pauseTime = time
        Log.d("WORKOUT REC", "pause workout")
    }

    var score = 0

    fun getCaloriesBurned(): Int {
        Log.e("CALORIES", "age : $age, weight: $weight, gender $gender, totatime : $totalTime ")
        return avgHR.findCalories(age, weight, gender, totalTime / 3600000f)
    }

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

    fun checkZoneChange(heartRate: Int): Boolean {
        heartRatePercent = heartRate
        totalHR += heartRate
        countHR++
        if (heartRate > maxHeartRate) maxHeartRate = heartRate
        Log.d("WORKOUT REC", "checkZone change : ${heartRatePercent / 10 != currZone}")
        return heartRatePercent / 10 != currZone
    }

    fun incrmentScore(inc: Int) {
        score += inc
    }

    fun onZoneChange(heartRate: Int, time: Long) {
        heartRatePercent = heartRate
        Log.d("WORKOUT REC", "zone change from $currZone to ${heartRatePercent / 10}")
        timeinZone[currZone] += (time - startTime).toInt()
        currZone = heartRatePercent / 10
        startTime = time
    }

    fun finishWorkout(time: Long): IntArray {
        timeinZone[currZone] += (time - startTime).toInt()
        Log.d("WORKOUT REC", "finish workout")
        return timeinZone
    }

    fun getWorkoutSummary(workoutId: String): WorkoutSummary {
        val zonedata = ArrayList<Int>(10)
        zonedata.addAll(arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0))
        timeinZone.forEachIndexed { index, i ->
            zonedata[index] = i
        }
        return WorkoutSummary(workoutId, Calendar.getInstance().timeInMillis, totalTime, avgHR, maxHeartRate, getCaloriesBurned(), score, zonedata)
    }
}
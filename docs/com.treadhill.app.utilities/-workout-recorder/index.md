[data](../../index.md) / [com.treadhill.app.utilities](../index.md) / [WorkoutRecorder](./index.md)

# WorkoutRecorder

(JVM) `class WorkoutRecorder`

recordes the Workout and generate Summary

### Constructors

| Name | Summary |
|---|---|
| (JVM) [&lt;init&gt;](-init-.md) | recordes the Workout and generate Summary`WorkoutRecorder(age: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, weight: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, gender: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, currZone: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0, timeinZone: `[`IntArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int-array/index.html)` = IntArray(10))` |

### Properties

| Name | Summary |
|---|---|
| (JVM) [age](age.md) | `val age: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| (JVM) [avgHR](avg-h-r.md) | calculate the get average Heart Rate`val avgHR: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| (JVM) [countHR](count-h-r.md) | `var countHR: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| (JVM) [currZone](curr-zone.md) | *Dont pass anything and keep the default value`var currZone: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| (JVM) [didWorkoutStart](did-workout-start.md) | `var didWorkoutStart: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| (JVM) [gender](gender.md) | `val gender: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| (JVM) [heartRatePercent](heart-rate-percent.md) | `var heartRatePercent: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| (JVM) [initStartTime](init-start-time.md) | `var initStartTime: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| (JVM) [maxHeartRate](max-heart-rate.md) | `var maxHeartRate: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| (JVM) [maxHR](max-h-r.md) | `val maxHR: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| (JVM) [pauseTime](pause-time.md) | `var pauseTime: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| (JVM) [score](score.md) | `var score: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| (JVM) [startTime](start-time.md) | `var startTime: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| (JVM) [timeinZone](timein-zone.md) | *Dont pass anything and keep the default value`var timeinZone: `[`IntArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int-array/index.html) |
| (JVM) [totalHR](total-h-r.md) | `var totalHR: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| (JVM) [totalTime](total-time.md) | `val totalTime: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| (JVM) [weight](weight.md) | `val weight: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

### Functions

| Name | Summary |
|---|---|
| (JVM) [checkZoneChange](check-zone-change.md) | Check if the new Heart Rate is in a different zone than previous`fun checkZoneChange(heartRate: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| (JVM) [finishWorkout](finish-workout.md) | Finishes the workout`fun finishWorkout(time: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`): `[`IntArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int-array/index.html) |
| (JVM) [getCaloriesBurned](get-calories-burned.md) | `fun getCaloriesBurned(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| (JVM) [getWorkoutSummary](get-workout-summary.md) | Call after finishing the workout`fun getWorkoutSummary(workoutId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`WorkoutSummary`](../../com.treadhill.app.data-types/-workout-summary/index.md) |
| (JVM) [incrmentScore](incrment-score.md) | Increase the score by the inc provided`fun incrmentScore(inc: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [onZoneChange](on-zone-change.md) | handle zone change - add the time in the previous zone and change the current zone`fun onZoneChange(heartRate: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, time: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [pauseWorkout](pause-workout.md) | Pause the recording of workout`fun pauseWorkout(time: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [resumeWorkout](resume-workout.md) | resume recording of workout`fun resumeWorkout(time: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`, heartRate: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [startWorkout](start-workout.md) | Start the workout`fun startWorkout(time: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`, heartRate: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

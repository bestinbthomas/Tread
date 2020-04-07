[data](../../index.md) / [com.treadhill.app.dataTypes](../index.md) / [WorkoutSummary](./index.md)

# WorkoutSummary

(JVM) `data class WorkoutSummary : `[`Parcelable`](https://developer.android.com/reference/android/os/Parcelable.html)

Summary of each workout

Stored in Firebase in the week collection

### Constructors

| Name | Summary |
|---|---|
| (JVM) [&lt;init&gt;](-init-.md) | Summary of each workout`WorkoutSummary(workoutId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "", timeStamp: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = 0, totalTime: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = 0, avgHeartRate: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0, maxHeartRate: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0, calories: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0, totalScore: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0, zoneInfo: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`> = List(10) { 0 })` |

### Properties

| Name | Summary |
|---|---|
| (JVM) [avgHeartRate](avg-heart-rate.md) | average heart rate`var avgHeartRate: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| (JVM) [calories](calories.md) | total calories burnt`var calories: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| (JVM) [maxHeartRate](max-heart-rate.md) | maximum heart rate reached durin gthe workout`var maxHeartRate: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| (JVM) [timeStamp](time-stamp.md) | time in milliseconds of when workout was done`var timeStamp: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| (JVM) [totalScore](total-score.md) | total score gained in the workout`var totalScore: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| (JVM) [totalTime](total-time.md) | total time spent doing the workout`var totalTime: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| (JVM) [workoutId](workout-id.md) | Id of workout`var workoutId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| (JVM) [zoneInfo](zone-info.md) | List of time spent in each zone in milliseconds`var zoneInfo: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |

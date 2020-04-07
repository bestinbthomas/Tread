[data](../../index.md) / [com.treadhill.app.utilities](../index.md) / [FirebaseUtils](./index.md)

# FirebaseUtils

(JVM) `class FirebaseUtils`

FirebaseUtils

### Constructors

| Name | Summary |
|---|---|
| (JVM) [&lt;init&gt;](-init-.md) | FirebaseUtils`FirebaseUtils()` |

### Properties

| Name | Summary |
|---|---|
| (JVM) [auth](auth.md) | `val auth: FirebaseAuth` |
| (JVM) [firedb](firedb.md) | `val firedb: FirebaseDatabase` |
| (JVM) [firestore](firestore.md) | `val firestore: FirebaseFirestore` |
| (JVM) [TAG](-t-a-g.md) | `val TAG: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Functions

| Name | Summary |
|---|---|
| (JVM) [getEmail](get-email.md) | `fun getEmail(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| (JVM) [getName](get-name.md) | `fun getName(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| (JVM) [getVideos](get-videos.md) | Get all videos from firebase firestore`suspend fun getVideos(): `[`Result`](../../com.treadhill.app.data-types/-result/index.md)`<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`VideoItem`](../../com.treadhill.app.data-types/-video-item/index.md)`>>` |
| (JVM) [getWeakMeta](get-weak-meta.md) | get the metadata for weak`suspend fun getWeakMeta(date: `[`Calendar`](https://docs.oracle.com/javase/8/docs/api/java/util/Calendar.html)`): `[`Result`](../../com.treadhill.app.data-types/-result/index.md)`<`[`WeakInfo`](../../com.treadhill.app.data-types/-weak-info/index.md)`>` |
| (JVM) [getWeakWorkouts](get-weak-workouts.md) | get Workouts and Meta data for the weak`suspend fun getWeakWorkouts(date: `[`Calendar`](https://docs.oracle.com/javase/8/docs/api/java/util/Calendar.html)`): `[`Result`](../../com.treadhill.app.data-types/-result/index.md)`<`[`Pair`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)`<`[`WeakInfo`](../../com.treadhill.app.data-types/-weak-info/index.md)`, `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`WorkoutSummary`](../../com.treadhill.app.data-types/-workout-summary/index.md)`>>>` |
| (JVM) [logout](logout.md) | log out in firebase`fun logout(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [postSummary](post-summary.md) | post summary to firebase firestore first gets the existing metadata then updates it and post it`suspend fun postSummary(workoutSummary: `[`WorkoutSummary`](../../com.treadhill.app.data-types/-workout-summary/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [postVideo](post-video.md) | posts video to firestore`fun postVideo(videoItems: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`VideoItem`](../../com.treadhill.app.data-types/-video-item/index.md)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [setHeartForWorkout](set-heart-for-workout.md) | uploads the heart rate to firebase realtime database`fun setHeartForWorkout(workoutid: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, value: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [setScoreForLeaderBoard](set-score-for-leader-board.md) | posts the score to Firebase realtime database for the user and leaderboard`fun setScoreForLeaderBoard(workoutid: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, score: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

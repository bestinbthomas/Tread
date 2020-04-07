[data](../../index.md) / [com.treadhill.app.utilities](../index.md) / [FirebaseUtils](index.md) / [getWeakWorkouts](./get-weak-workouts.md)

# getWeakWorkouts

(JVM) `suspend fun getWeakWorkouts(date: `[`Calendar`](https://docs.oracle.com/javase/8/docs/api/java/util/Calendar.html)`): `[`Result`](../../com.treadhill.app.data-types/-result/index.md)`<`[`Pair`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)`<`[`WeakInfo`](../../com.treadhill.app.data-types/-weak-info/index.md)`, `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`WorkoutSummary`](../../com.treadhill.app.data-types/-workout-summary/index.md)`>>>`

get Workouts and Meta data for the weak

PATH : firestore.collection("users").document(auth.currentUser!!.uid).collection(weak).get()

### Parameters

`date` - 
[data](../../index.md) / [com.treadhill.app.utilities](../index.md) / [FirebaseUtils](index.md) / [setHeartForWorkout](./set-heart-for-workout.md)

# setHeartForWorkout

(JVM) `fun setHeartForWorkout(workoutid: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, value: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

uploads the heart rate to firebase realtime database

Path : firedb.reference.child(auth.currentUser?.displayName ?: "user").child(workoutid)
.child(Calendar.getInstance().timeInMillis.toString()).setValue(value)

### Parameters

`workoutid` -

`value` - 
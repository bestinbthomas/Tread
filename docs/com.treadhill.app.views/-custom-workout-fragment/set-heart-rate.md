[data](../../index.md) / [com.treadhill.app.views](../index.md) / [CustomWorkoutFragment](index.md) / [setHeartRate](./set-heart-rate.md)

# setHeartRate

(JVM) `private fun setHeartRate(heartRate: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

update the UI after recieving the HR
calls checkZoneChange(this.heartRate) on WorkoutRecorder,
gets calories from recorder

### Parameters

`heartRate` - 
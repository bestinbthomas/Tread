[data](../../index.md) / [com.treadhill.app.utilities](../index.md) / [FirebaseUtils](index.md) / [postSummary](./post-summary.md)

# postSummary

(JVM) `suspend fun postSummary(workoutSummary: `[`WorkoutSummary`](../../com.treadhill.app.data-types/-workout-summary/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

post summary to firebase firestore
first gets the existing metadata
then updates it and post it

metadata PATH : firestore.collection("users").document(auth.currentUser!!.uid).collection(weak).document("metadata").set(modifiedMetadata)
summary PATH : firestore.collection("users").document(auth.currentUser!!.uid).collection(weak).add(workoutSummary)

### Parameters

`workoutSummary` - 
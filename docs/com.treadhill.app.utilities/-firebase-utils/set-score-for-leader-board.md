[data](../../index.md) / [com.treadhill.app.utilities](../index.md) / [FirebaseUtils](index.md) / [setScoreForLeaderBoard](./set-score-for-leader-board.md)

# setScoreForLeaderBoard

(JVM) `fun setScoreForLeaderBoard(workoutid: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, score: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

posts the score to Firebase realtime database for the user and leaderboard

for the user record PATH : firedb.reference.child(auth.currentUser?.displayName ?: "user").child(workoutid)
.child(Calendar.getInstance().timeInMillis.toString()).setValue(score)

For Leaderboard PATH : firedb.reference.child(workoutid).child(auth.currentUser?.displayName ?: "user").setValue(score)

### Parameters

`workoutid` -

`score` - 
package com.treadhill.app.utilities

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.treadhill.app.dataTypes.Result
import com.treadhill.app.dataTypes.VideoItem
import com.treadhill.app.dataTypes.WeakInfo
import com.treadhill.app.dataTypes.WorkoutSummary
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.*
import kotlin.collections.ArrayList
import kotlin.coroutines.resume

/**
 * FirebaseUtils
 */
class FirebaseUtils {
    private val TAG = "FIREBASE"
    private val firedb = Firebase.database
    private val firestore = Firebase.firestore
    private val auth = FirebaseAuth.getInstance()

    /**
     * uploads the heart rate to firebase realtime database
     *
     * Path : firedb.reference.child(auth.currentUser?.displayName ?: "user").child(workoutid)
    .child(Calendar.getInstance().timeInMillis.toString()).setValue(value)
     *
     * @param workoutid
     * @param value
     */
    fun setHeartForWorkout(workoutid: String, value: Int) {
        Log.e(TAG, "value sent is $value")
        firedb.reference.child(auth.currentUser?.displayName ?: "user").child(workoutid)
            .child(Calendar.getInstance().timeInMillis.toString()).setValue(value)
            .addOnFailureListener {
                Log.e(TAG, "set heart rate value failed", it)
            }
    }

    /**
     * posts the score to Firebase realtime database for the user and leaderboard
     *
     * for the user record PATH : firedb.reference.child(auth.currentUser?.displayName ?: "user").child(workoutid)
    .child(Calendar.getInstance().timeInMillis.toString()).setValue(score)
     *
     * For Leaderboard PATH : firedb.reference.child(workoutid).child(auth.currentUser?.displayName ?: "user").setValue(score)
     *
     * @param workoutid
     * @param score
     */
    fun setScoreForLeaderBoard(workoutid: String, score: Int) {
        firedb.reference.child(workoutid).child(auth.currentUser?.displayName ?: "user")
            .setValue(score)
            .addOnFailureListener {
                Log.e(TAG, "setting high score failed", it)
            }
        firedb.reference.child(auth.currentUser?.displayName ?: "user").child(workoutid)
            .child(Calendar.getInstance().timeInMillis.toString()).setValue(score)
            .addOnFailureListener {
                Log.e(TAG, "set heart rate value failed", it)
            }
    }

    /**
     * log out in firebase
     *
     */
    fun logout() = auth.signOut()

    fun getName(): String? = auth.currentUser?.displayName
    fun getEmail(): String? = auth.currentUser?.email

    /**
     * posts video to firestore
     *
     * PATH : firestore.collection("videos").document(video.name).set(video)
     *
     * @param videoItems
     */
    fun postVideo(videoItems: List<VideoItem>) {
        videoItems.forEach { video ->
            firestore.collection("videos").document(video.name).set(video)
        }
    }

    /**
     * Get all videos from firebase firestore
     *
     */
    suspend fun getVideos() = suspendCancellableCoroutine<Result<List<VideoItem>>> { continuation ->
        firestore.collection("videos").get()
            .addOnSuccessListener { querrySnapshot ->
                val videoList: List<VideoItem> = querrySnapshot.documents.map { documentSnapshot ->
                    documentSnapshot.toObject(VideoItem::class.java)!!
                }

                continuation.resume(Result(videoList))
            }
            .addOnFailureListener {
                continuation.resume(Result(it))
            }
    }

    /**
     * get Workouts and Meta data for the weak
     *
     * PATH : firestore.collection("users").document(auth.currentUser!!.uid).collection(weak).get()
     *
     * @param date
     */
    suspend fun getWeakWorkouts(date: Calendar) =
        suspendCancellableCoroutine<Result<Pair<WeakInfo, List<WorkoutSummary>>>> { continuation ->
            val weak = "${date[Calendar.YEAR]} weak: ${date[Calendar.WEEK_OF_YEAR]}"
            Log.i("FIRE UTILS", " GET WEAK INFO FOR $weak ")
            firestore.collection("users").document(auth.currentUser!!.uid).collection(weak)
                .get()
                .addOnSuccessListener { querrySnapshot ->
                    var weakInfo = WeakInfo()
                    val workoutSummaries = ArrayList<WorkoutSummary>()
                    querrySnapshot.documents.forEach { documentSnapshot ->
                        if (documentSnapshot.contains("workoutId")) {
                            Log.i("FIRE UTILS", "recieved summary")
                            workoutSummaries.add(documentSnapshot.toObject(WorkoutSummary::class.java)!!)
                        } else {
                            Log.i("FIRE UTILS", "recieved info")
                            weakInfo = documentSnapshot.toObject(WeakInfo::class.java) ?: WeakInfo()
                            Log.i("FIRE UTILS", "weakinfo recieved ${weakInfo.scores} : ${weakInfo.duration} : ${weakInfo.calories}")
                        }
                    }

                    continuation.resume(Result(Pair(weakInfo, workoutSummaries)))
                }
                .addOnFailureListener { exception ->
                    continuation.resume(Result(exception))
                }

        }

    /**
     * get the metadata for weak
     *
     * PATH : firestore.collection("users").document(auth.currentUser!!.uid).collection(weak).document("metadata").get()
     *
     * @param date
     */
    suspend fun getWeakMeta(date: Calendar) =
        suspendCancellableCoroutine<Result<WeakInfo>> { continuation ->
            val weak = "${date[Calendar.YEAR]} weak: ${date[Calendar.WEEK_OF_YEAR]}"
            Log.i("FIRE UTILS", " GET WEAK INFO FOR $weak ")
            firestore.collection("users").document(auth.currentUser!!.uid).collection(weak).document("metadata").get()
                .addOnSuccessListener {
                    Log.i("FIRE UTILS", "recieved WekMeta")
                    continuation.resume(Result(it.toObject(WeakInfo::class.java)))
                }
                .addOnFailureListener {
                    continuation.resume(Result(it))
                }
        }

    /**
     * post summary to firebase firestore
     * first gets the existing metadata
     * then updates it and post it
     *
     * metadata PATH : firestore.collection("users").document(auth.currentUser!!.uid).collection(weak).document("metadata").set(modifiedMetadata)
     * summary PATH : firestore.collection("users").document(auth.currentUser!!.uid).collection(weak).add(workoutSummary)
     *
     * @param workoutSummary
     */
    suspend fun postSummary(workoutSummary: WorkoutSummary) {
        val dateNow = Calendar.getInstance()
        val weakMetaResult = getWeakMeta(dateNow)
        Log.i("FIRE UTILS", "post summary called")
        if (weakMetaResult.exception == null) {
            val weakInfo = weakMetaResult.value ?: WeakInfo()
            val weak = "${dateNow[Calendar.YEAR]} weak: ${dateNow[Calendar.WEEK_OF_YEAR]}"
            val newScores = weakInfo.scores as ArrayList
            newScores[dateNow[Calendar.DAY_OF_WEEK] - 1] += workoutSummary.totalScore.toLong()
            val newDuration = weakInfo.duration as ArrayList
            newDuration[dateNow[Calendar.DAY_OF_WEEK] - 1] += workoutSummary.totalTime
            val newCalories = weakInfo.calories as ArrayList
            newCalories[dateNow[Calendar.DAY_OF_WEEK] - 1] += workoutSummary.calories.toLong()

            val modifiedMetadata = WeakInfo(newScores, newDuration, newCalories)

            firestore.collection("users").document(auth.currentUser!!.uid).collection(weak).document("metadata").set(modifiedMetadata)
            firestore.collection("users").document(auth.currentUser!!.uid).collection(weak)
                .add(workoutSummary)
        }
    }

}
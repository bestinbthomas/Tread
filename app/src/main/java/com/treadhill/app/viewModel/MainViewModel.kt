package com.treadhill.app.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.treadhill.app.dataTypes.FilterOptions
import com.treadhill.app.dataTypes.VideoItem
import com.treadhill.app.dataTypes.WeakInfo
import com.treadhill.app.dataTypes.WorkoutSummary
import com.treadhill.app.utilities.FirebaseUtils
import com.treadhill.app.utilities.VimeoUtils
import com.treadhill.app.viewModel.Actions.*
import com.vimeo.networking.model.Video
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*
import kotlin.coroutines.CoroutineContext

class MainViewModel(val context: Context) : ViewModel(), CoroutineScope {

    private val vimeoUtils = VimeoUtils.getInstance(context)
    private val firebaseUtils = FirebaseUtils()
    private var allVideos = listOf<VideoItem>()

    private val mutableEmail = MutableLiveData<String?>()
    val email: LiveData<String?>
        get() = mutableEmail

    //Video List
    private val mutableVideoList = MutableLiveData<List<Video>>()
    val videoList: LiveData<List<Video>>
        get() = mutableVideoList

    //Video List
    private val mutableTreadVideoList = MutableLiveData<List<VideoItem>>()
    val treadVideoList: LiveData<List<VideoItem>>
        get() = mutableTreadVideoList

    //selected video
    private val mutableSelectedVideo = MutableLiveData<Video>()
    val selectedVideo: LiveData<Video>
        get() = mutableSelectedVideo

    private val mutableHeartRate = MutableLiveData<String>("85")
    val heartRate: LiveData<String>
        get() = mutableHeartRate

    val scanBT = MutableLiveData<Boolean>()

    private val mutableBTState = MutableLiveData<Int>()
    val bTState: LiveData<Int>
        get() = mutableBTState

    private val mutableWeakInfo = MutableLiveData<WeakInfo>()
    val weakInfo: LiveData<WeakInfo>
        get() = mutableWeakInfo

    private val mutableWeakWorkoutSummaries = MutableLiveData<List<WorkoutSummary>>()
    val weakWorkoutSummaries: LiveData<List<WorkoutSummary>>
        get() = mutableWeakWorkoutSummaries

    private val mutableFilterOptions = MutableLiveData(FilterOptions())
    val filterOptions: LiveData<FilterOptions>
        get() = mutableFilterOptions

    private val mutableNowPlayingVideo = MutableLiveData<Video>()
    val nowPlayingVideo: LiveData<Video>
        get() = mutableNowPlayingVideo

    val filterSearch = MutableLiveData<Boolean>()

    /**
     * get videos from vimeo and DB
     */
    init {
        mutableEmail.value = firebaseUtils.getEmail()
        launch {
            val vimeoVids = vimeoUtils.getUserVids()
            mutableVideoList.value = vimeoVids
            val videoItems = vimeoVids.map { vimeoVideo ->
                VideoItem(
                    name = vimeoVideo.name,
                    CreatorID = null,
                    WorkoutID = null,
                    vimeoUri = vimeoVideo.uri,
                    VideoDescription = vimeoVideo.description,
                    VideoCategory = null,
                    rating = null,
                    LevelOfDifficulty = null,
                    UploadDate = vimeoVideo.createdTime.time,
                    ThumbnailUrl = vimeoVideo.pictures?.pictureForWidth(400)?.link,
                    NoOfUniqueViewers = null,
                    Calories = null,
                    FocusArea = null,
                    Equipment = null,
                    CreatorName = null,
                    VideoLength = vimeoVideo.duration,
                    MusicType = null
                )
            }

//             to post the vimeo video to firebase
//            firebaseUtils.postVideo(videoItems)

        }

        launch {
            val result = firebaseUtils.getVideos()
            if (result.value != null) {
                allVideos = result.value
                mutableTreadVideoList.value = result.value
            }
        }


    }

    /**
     * handle commands from activity or fragment
     *
     * @param action
     * @return
     */
    fun listen(action: Actions): Any = when (action) {
        is SearchChange -> filterVideos(FilterOptions(query = action.querry ?: ""))
        is PostHeartRate -> postHeartrate(action.workoutId, action.value)
        Logout -> {
            firebaseUtils.logout()
        }
        is HeartRate -> mutableHeartRate.value = action.data
        is BluetoothState -> mutableBTState.value = action.state
        ScanBluetooth -> scanBT.value = true
        Filter -> filterSearch.value = true
        is PostWorkoutSummary -> postWorkoutSummary(action.workoutSummary)
        is PostScore -> postScore(action.workoutId, action.score)
        is GetWorkoutsInWeak -> getWorkoutsInWeak(action.date)
        is GetWeekInfo -> getWorkoutsInWeak(action.date)
        is FilterVideos -> filterVideos(action.options)
        is GetVimeoVideo -> getVimeoVideo(action.uri)
    }

    private fun getVimeoVideo(uri: String) = launch {
        val video = vimeoUtils.getVideo(uri)
        video.value?.let {
            mutableNowPlayingVideo.value = it
        }
        video.exception?.let {
            Log.e("Vimeo ERROR", "failed to get video", it)
        }
    }

    private fun filterVideos(options: FilterOptions) {
        mutableFilterOptions.value = options
        mutableTreadVideoList.value = options.filter(allVideos)
    }

    private fun postScore(workoutId: String, score: Int) = launch {
        Log.i("VIW MODEL", "post score : $score for worout : $workoutId")
        firebaseUtils.setScoreForLeaderBoard(workoutId, score)
    }

    private fun getWorkoutsInWeak(date: Calendar) = launch {

        mutableWeakInfo.value = null
        mutableWeakWorkoutSummaries.value = null

        val res = firebaseUtils.getWeakWorkouts(date)
        if (res.value != null) {
            Log.i("VEW MODEL", "weakinfo recieved ${res.value.first.scores} : ${res.value.first.duration} : ${res.value.first.calories}")
            mutableWeakInfo.value = res.value.first
            mutableWeakWorkoutSummaries.value = res.value.second.sortedByDescending { it.timeStamp }
        }

    }

    private fun postWorkoutSummary(workoutSummary: WorkoutSummary) = launch {
        Log.i("VIEW MODEL", "post workout summay called")
        firebaseUtils.postSummary(workoutSummary)
    }

    private fun postHeartrate(workoutId: String, value: Int) {
        firebaseUtils.setHeartForWorkout(workoutId, value)
    }

    override val coroutineContext: CoroutineContext
        get() = viewModelScope.coroutineContext
}

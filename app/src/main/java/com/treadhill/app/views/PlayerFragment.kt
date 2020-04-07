package com.treadhill.app.views


import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import aqua.blewrapper.connectionstates.StateCodes
import coil.api.load
import coil.transform.CircleCropTransformation
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.treadhill.app.R
import com.treadhill.app.adapters.LeaderBoardRecAdapter
import com.treadhill.app.dataTypes.LeaderboardItem
import com.treadhill.app.dataTypes.VideoItem
import com.treadhill.app.factory_and_ingection.InjectionUtils
import com.treadhill.app.highOrder.*
import com.treadhill.app.utilities.ExoUtils
import com.treadhill.app.utilities.WorkoutRecorder
import com.treadhill.app.viewModel.Actions
import com.treadhill.app.viewModel.MainViewModel
import com.treadhill.app.views.customViews.Slider
import com.vimeo.networking.model.Video
import kotlinx.android.synthetic.main.exo_controls.*
import kotlinx.android.synthetic.main.fragment_player.view.*
import java.util.*
import kotlin.collections.ArrayList

class PlayerFragment : Fragment(), Player.EventListener {

    /**
     * video from Firebase DB
     */
    private lateinit var treadVideo: VideoItem
    private var mExoplayerFullScreen: Boolean = false

    //private lateinit var mFullScreenDialog: Dialog
    var player: ExoPlayer? = null
    var playbackPosition: Long = 0
    var playWhenReady = false
    var currentWindow: Int = 0
    var mediaSource: MediaSource? = null
    lateinit var exoutils: ExoUtils
    private lateinit var playerView: PlayerView
    private lateinit var vidHeartRate: TextView
    private lateinit var heratrateSlider: Slider

    /**
     * Video recieived from Vimeo
     */
    private var video: Video? = null
    private lateinit var mView: View
    private lateinit var viewModel: MainViewModel
    private var isPlaying: Boolean = false
    private val leaderboardAdapter = LeaderBoardRecAdapter()
    private var isLeaderboardOpen = false
    private var isDviceConnected = false
    private var didworkoutStart = false
    private var timestamp = Calendar.getInstance().timeInMillis

    //    var turn:Int = 0
//    var turn2:Int = 0
    private var heartRate = 0
    private var workoutId = ""
    private var zoneIndex = 0
    var currZone = 0

    /**
     * Timer that updates the zone every 10 seconds checking the
     */
    val timer = object : CountDownTimer(10000000000000, 10000) {
        override fun onFinish() {
        }

        override fun onTick(millisUntilFinished: Long) {
            if (isPlaying) {
                currZone = treadVideo.taretZones?.get(zoneIndex % (treadVideo.taretZones?.size
                    ?: 1)) ?: 0
                Log.i("PLAYER", "move to zone : $currZone")
                mView.heart_slider.moveSlider(currZone)
                zoneIndex++
            }
        }

    }

    private lateinit var workoutRecorder: WorkoutRecorder

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("player", "create called")
        return inflater.inflate(R.layout.fragment_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("player", "view created called")
        mView = view
    }


    override fun onStart() {
        super.onStart()
        Log.e("player", "start called")

        viewModel = ViewModelProviders.of(
            requireActivity(),
            InjectionUtils.provideMainViewModelFactory(requireActivity().applicationContext)
        ).get(MainViewModel::class.java)

        val preferences = requireActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val dob = preferences.getLong(PREF_DOB, 0)

        val dobCal = Calendar.getInstance()
        dobCal.timeInMillis = dob

        val age = Calendar.getInstance()[Calendar.YEAR] - dobCal[Calendar.YEAR]

        workoutRecorder = WorkoutRecorder(
            age,
            preferences.getInt(PREF_WEIGHT, 50),
            preferences.getString(PREF_GENDER, "M")!!
        )

        if (player == null) {
            player = ExoPlayerFactory.newSimpleInstance(
                requireContext(),
                DefaultRenderersFactory(requireContext()),
                DefaultTrackSelector(),
                DefaultLoadControl()
            )
        }

        player?.addListener(this)


        //video = navArgs<PlayerActivityArgs>().value.video
        video = navArgs<PlayerFragmentArgs>().value.video
        treadVideo = navArgs<PlayerFragmentArgs>().value.treadVideo
        playerView = mView.findViewById(R.id.player_view)
        vidHeartRate = mView.findViewById(R.id.video_heart_rate)
        heratrateSlider = mView.findViewById(R.id.heart_slider)

        if (video == null) {
            viewModel.listen(Actions.GetVimeoVideo(treadVideo.vimeoUri!!))
        }

        workoutId = video?.name ?: treadVideo.WorkoutID ?: treadVideo.name
        treadVideo.ThumbnailUrl?.let {
            mView.creator_image.load(it) {
                transformations(CircleCropTransformation())
            }
        }

        val width = resources.displayMetrics.widthPixels

        var ratio = (video?.height?.toFloat() ?: 1f) / (video?.width?.toFloat() ?: 2f)
        if (ratio < 0.5) ratio = 0.5f

        val heightpixels = ratio * width

        Log.i("PARAMS", "width : $width ratio : $ratio height : $heightpixels")
        mView.player_container.layoutParams = LinearLayout.LayoutParams(width, heightpixels.toInt())


        exoutils = ExoUtils(requireActivity())

        //initFullScreenDialog()

        setObservations()
        setOnClicks()
        setUI()
//        setBenifits()
        initLeaderboard()

    }

    /**
     * getting data from the Leaderboard and Listening for updates
     *
     */
    private fun initLeaderboard() {
        mView.leaderboard.adapter = leaderboardAdapter
        val leaderboardList = ArrayList<LeaderboardItem>()
        Log.i("workout id", workoutId)
        FirebaseDatabase.getInstance().reference.child(workoutId)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    showSnackbar(p0.message)
                    Log.e("leagerboard", p0.details, p0.toException())
                }

                override fun onDataChange(p0: DataSnapshot) {
                    leaderboardAdapter.submitList(p0.children.map {
                        Log.i("leaderboard", "${it.key} : ${it.value}")
                        LeaderboardItem(
                            R.mipmap.avatar1,
                            it.key ?: "",
                            (it.getValue(Int::class.java) ?: 0)
                        )
                    })
                }

            })
        leaderboardAdapter.submitList(leaderboardList)
        mView.leaderboard.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

    }

    /**
     * observe HeartRate, BluetoothState Live data from ViewModel
     *
     */
    private fun setObservations() {
        viewModel.heartRate.observe(this,
            Observer {
                Log.e("PLayer", "data = $it recieved")
                heartRate = it.toInt()
                vidHeartRate.text = it
                heratrateSlider.heartRate = it.toFloat()
                if (isPlaying) {
                    viewModel.listen(Actions.PostHeartRate("$workoutId : $timestamp", it.toInt()))
                    workoutRecorder.incrmentScore(calculateScore(it.toInt()))
                    viewModel.listen(Actions.PostScore(workoutId, workoutRecorder.score))
                    if (workoutRecorder.checkZoneChange(it.toInt())) {
                        workoutRecorder.onZoneChange(it.toInt(), SystemClock.elapsedRealtime())
                    }
                    mView.video_calories.text = workoutRecorder.getCaloriesBurned().toString()
                } else mView.video_calories.text = "0"
                Log.e(
                    "WORKOUT",
                    "avgHR : ${workoutRecorder.avgHR} time = ${workoutRecorder.totalTime / 3600000}"
                )
            })
        viewModel.bTState.observe(this,
            Observer {
                when (it) {
                    StateCodes.STATE_ENABLED -> hideHR()
                    StateCodes.STATE_DISABLED -> hideHR()
                    StateCodes.STATE_ENABLED_ALREADY -> hideHR()
                    StateCodes.STATE_DISBALED_ALREADY -> hideHR()
                    StateCodes.BluetoothTurnedOn -> hideHR()
                    StateCodes.BluetoothTurnedOff -> hideHR()
                    StateCodes.DeviceConnected -> showHR()
                    StateCodes.DeviceDisconnected -> hideHR()
                    StateCodes.RetryConnection -> hideHR()
                }
            })
    }

    /**
     * Hide the Heart rate
     *
     */
    private fun hideHR() {
        isDviceConnected = false
        vidHeartRate.visibility = View.INVISIBLE
        mView.heart_slider.visibility = View.INVISIBLE
        mView.video_calories.visibility = View.GONE
    }

    /**
     * Show the Heart rate
     *
     */
    private fun showHR() {
        isDviceConnected = true
        vidHeartRate.visibility = View.VISIBLE
        mView.heart_slider.visibility = View.VISIBLE
        mView.video_calories.visibility = View.VISIBLE
    }

//    private fun setBenifits(benifits: MutableList<String> = mutableListOf()) {
//        Log.e("player", "benifits called")
//        if (benifits.isEmpty()) {
//            benifits.add("benifit 1")
//            benifits.add("benifit 2")
//            benifits.add("benifit 3")
//            benifits.add("benifit 4")
//        }
//        mView.benifits_list.divider = null
//        mView.benifits_list.adapter =
//            ArrayAdapter<String>(requireContext(), R.layout.benifit_item, benifits)
//        val child = mView.benifits_list.adapter.getView(0, null, mView.benifits_list)
//        val measureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
//        child.measure(measureSpec, measureSpec)
//        val itemHeight = child.measuredHeight
//        val params = mView.benifits_list.layoutParams
//        params.height = itemHeight * benifits.size
//        mView.benifits_list.layoutParams = params
//    }

    /**
     * set the Ui elements Like title, difficulty, etc form the Video
     *
     */
    private fun setUI() {
        Log.e("player", "setui called")
        mView.duration.text = resources.getString(R.string.duration, this.treadVideo.VideoLength)
        mView.difficulty.text = treadVideo.LevelOfDifficulty
        mView.calories.text = treadVideo.Calories?.toString() + " cal"
        mView.Tittle.text = treadVideo.name
        mView.description.text = treadVideo.VideoDescription
        mView.creator_name.text = treadVideo.CreatorName
    }

    /**
     * sets OnClickListeners to Fullscreen, Stop Workout and Leaderboard Toggle
     *
     */
    private fun setOnClicks() {
        Log.e("player", "onclick called")
        exo_fullscreen_icon.setOnClickListener {
            if (!mExoplayerFullScreen)
                activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            else
                activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        mView.timer_stop.setOnClickListener {
            if (workoutRecorder.startTime != 0.toLong()) {
                val preferences = requireActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
                val dob = preferences.getLong(PREF_DOB, 0)

                val dobCal = Calendar.getInstance()
                dobCal.timeInMillis = dob

                val age = Calendar.getInstance()[Calendar.YEAR] - dobCal[Calendar.YEAR]
                player!!.currentPosition

                val workoutSummary = workoutRecorder.getWorkoutSummary(workoutId)
                viewModel.listen(Actions.PostWorkoutSummary(workoutSummary))
                workoutRecorder = WorkoutRecorder(
                    age,
                    preferences.getInt(PREF_WEIGHT, 50),
                    preferences.getString(PREF_GENDER, "M")!!
                )
                findNavController().navigate(
                    PlayerFragmentDirections.actionPlayerFragmentToWorkoutSummaryFragment(
                        workoutSummary
                    )
                )
            } else showSnackbar("You did not start workout", Snackbar.LENGTH_SHORT)
        }
        mView.video_heart_rate.setOnClickListener {
            Log.i("PLAYER", "calories clicked")
            if (isLeaderboardOpen) {
                isLeaderboardOpen = false
                mView.leaderboard.visibility = View.GONE
                mView.video_calories.visibility = View.VISIBLE
            } else {
                isLeaderboardOpen = true
                mView.leaderboard.visibility = View.VISIBLE
                mView.video_calories.visibility = View.GONE
            }
        }
    }

    override fun onResume() {
        if (!didworkoutStart) {
            super.onResume()
            Log.e("player", "resume called")
            video?.let {
                initializePlayer(it)
            }
            viewModel.nowPlayingVideo.observe(this,
                Observer {
                    initializePlayer(it)
                })
        }
    }

    override fun onPause() {
        if (!didworkoutStart) {
            super.onPause()
            Log.e("player", "pause called")
            releasePlayer()
        }
    }

    override fun onStop() {
        if (!didworkoutStart) {
            super.onStop()
            Log.e("player", "stop called")
            releasePlayer()
        }
    }

    /**
     * Get the MediaSource for exoplayer using ExoUtils and set the player
     *
     * @param video this the video recieved from vimeo
     */
    private fun initializePlayer(video: Video) {
        //Log.i("EXO","video null in init")
        Log.e("player", "init player called")

        mediaSource = exoutils.getMediaSource(video)
        Log.i("EXO", "media source" + mediaSource.toString())
        mediaSource?.let {
            Log.i("EXO", "player preparing")
            player?.playWhenReady = playWhenReady
            player?.seekTo(currentWindow, playbackPosition)
            player?.prepare(it, false, false)
            playerView.player = player

            Log.e("EXO", "player ${playerView}")
            Log.e("EXO", "player player${playerView.player}")
        }
    }

    /**
     * release the player on Stop to avoid memory leaks
     *
     */
    private fun releasePlayer() {
        Log.e("player", "release player called")
        if (player != null) {
            playWhenReady = player?.playWhenReady ?: true
            playbackPosition = player?.currentPosition ?: 0
            currentWindow = player?.currentWindowIndex ?: 0
            player?.playWhenReady = false
            player?.release()
            player = null
            Log.e("EXO", "player released")
        }
    }


    override fun onTracksChanged(
        trackGroups: TrackGroupArray?,
        trackSelections: TrackSelectionArray?
    ) {
        Log.i("EXO", "tracks changed")
    }

    override fun onPlayerError(error: ExoPlaybackException?) {
        Log.e("EXO ERROR", "exoplayer failed", error)
    }

    override fun onLoadingChanged(isLoading: Boolean) {
        Log.i("EXO", if (isLoading) "Player loading" else "Player stopped loading")

    }

    override fun onIsPlayingChanged(isPlaying: Boolean) {
        this.isPlaying = isPlaying
        if (this.isPlaying) {
            timer.start()
            if (!workoutRecorder.didWorkoutStart)
                workoutRecorder.startWorkout(SystemClock.elapsedRealtime(), heartRate)
            else workoutRecorder.resumeWorkout(SystemClock.elapsedRealtime(), heartRate)
        } else {
            workoutRecorder.pauseWorkout(SystemClock.elapsedRealtime())
        }
        Log.i("EXO", if (isPlaying) "Player playing" else "Player stopped playing")
    }

    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        super.onPlayerStateChanged(playWhenReady, playbackState)
        Log.e("player", "playerstatechanged called")
        if (playbackState == ExoPlayer.STATE_BUFFERING) {
            mView.progress_bar.visibility = View.VISIBLE
        } else {
            mView.progress_bar.visibility = View.INVISIBLE
        }

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.e("player", "config change called")
        // Checking the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mExoplayerFullScreen = true
            requireActivity().window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            mView.details_group.visibility = View.GONE
            val params = playerView.layoutParams as FrameLayout.LayoutParams
            params.width = FrameLayout.LayoutParams.MATCH_PARENT
            params.height = FrameLayout.LayoutParams.MATCH_PARENT
            val layoutParams = mView.player_container.layoutParams
            layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT
            layoutParams.height = LinearLayout.LayoutParams.MATCH_PARENT
            exo_fullscreen_icon.setImageResource(R.drawable.ic_fullscreen_collapse)
            mView.player_container.layoutParams = layoutParams
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            mExoplayerFullScreen = false
            requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
            exo_fullscreen_icon.setImageResource(R.drawable.ic_fullscreen_expand)
            mView.details_group.visibility = View.VISIBLE
            val params = playerView.layoutParams as FrameLayout.LayoutParams
            params.width = FrameLayout.LayoutParams.MATCH_PARENT
            params.height = FrameLayout.LayoutParams.WRAP_CONTENT
            val layoutParams = mView.player_container.layoutParams
            layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT
            layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT
            mView.player_container.layoutParams = layoutParams
        }
    }

    /**
     * Get the zone (0 - 5) from the given Heart rate
     *
     * @param heartRate
     */
    private fun getZone(heartRate: Int) = ((heartRate / workoutRecorder.maxHR) * 10).toInt() - 5

    /**
     * Calculates the score for given heart rate
     *
     * @param heartRate
     * @return score
     */
    private fun calculateScore(heartRate: Int): Int {
        val zone = getZone(heartRate)
        if (zone == currZone) return 4
        if (zone == currZone + 1) return 3
        if (currZone > 1 && zone == currZone - 1) return 3

        return 0
    }

}

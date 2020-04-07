[data](../../index.md) / [com.treadhill.app.views](../index.md) / [PlayerFragment](./index.md)

# PlayerFragment

(JVM) `class PlayerFragment : Fragment, EventListener`

### Constructors

| Name | Summary |
|---|---|
| (JVM) [&lt;init&gt;](-init-.md) | `PlayerFragment()` |

### Properties

| Name | Summary |
|---|---|
| (JVM) [currentWindow](current-window.md) | `var currentWindow: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| (JVM) [currZone](curr-zone.md) | `var currZone: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| (JVM) [didworkoutStart](didworkout-start.md) | `var didworkoutStart: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| (JVM) [exoutils](exoutils.md) | `lateinit var exoutils: `[`ExoUtils`](../../com.treadhill.app.utilities/-exo-utils/index.md) |
| (JVM) [heartRate](heart-rate.md) | `var heartRate: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| (JVM) [heratrateSlider](heratrate-slider.md) | `lateinit var heratrateSlider: `[`Slider`](../../com.treadhill.app.views.custom-views/-slider/index.md) |
| (JVM) [isDviceConnected](is-dvice-connected.md) | `var isDviceConnected: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| (JVM) [isLeaderboardOpen](is-leaderboard-open.md) | `var isLeaderboardOpen: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| (JVM) [isPlaying](is-playing.md) | `var isPlaying: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| (JVM) [leaderboardAdapter](leaderboard-adapter.md) | `val leaderboardAdapter: `[`LeaderBoardRecAdapter`](../../com.treadhill.app.adapters/-leader-board-rec-adapter/index.md) |
| (JVM) [mediaSource](media-source.md) | `var mediaSource: MediaSource?` |
| (JVM) [mExoplayerFullScreen](m-exoplayer-full-screen.md) | `var mExoplayerFullScreen: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| (JVM) [mView](m-view.md) | `lateinit var mView: `[`View`](https://developer.android.com/reference/android/view/View.html) |
| (JVM) [playbackPosition](playback-position.md) | `var playbackPosition: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| (JVM) [player](player.md) | `var player: ExoPlayer?` |
| (JVM) [playerView](player-view.md) | `lateinit var playerView: PlayerView` |
| (JVM) [playWhenReady](play-when-ready.md) | `var playWhenReady: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| (JVM) [timer](timer.md) | Timer that updates the zone every 10 seconds checking the`val timer: `[`CountDownTimer`](https://developer.android.com/reference/android/os/CountDownTimer.html) |
| (JVM) [timestamp](timestamp.md) | `var timestamp: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| (JVM) [treadVideo](tread-video.md) | video from Firebase DB`lateinit var treadVideo: `[`VideoItem`](../../com.treadhill.app.data-types/-video-item/index.md) |
| (JVM) [video](video.md) | Video recieived from Vimeo`var video: Video?` |
| (JVM) [vidHeartRate](vid-heart-rate.md) | `lateinit var vidHeartRate: `[`TextView`](https://developer.android.com/reference/android/widget/TextView.html) |
| (JVM) [viewModel](view-model.md) | `lateinit var viewModel: `[`MainViewModel`](../../com.treadhill.app.view-model/-main-view-model/index.md) |
| (JVM) [workoutId](workout-id.md) | `var workoutId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| (JVM) [workoutRecorder](workout-recorder.md) | `lateinit var workoutRecorder: `[`WorkoutRecorder`](../../com.treadhill.app.utilities/-workout-recorder/index.md) |
| (JVM) [zoneIndex](zone-index.md) | `var zoneIndex: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

### Functions

| Name | Summary |
|---|---|
| (JVM) [calculateScore](calculate-score.md) | Calculates the score for given heart rate`fun calculateScore(heartRate: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| (JVM) [getZone](get-zone.md) | Get the zone (0 - 5) from the given Heart rate`fun getZone(heartRate: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| (JVM) [hideHR](hide-h-r.md) | Hide the Heart rate`fun hideHR(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [initializePlayer](initialize-player.md) | Get the MediaSource for exoplayer using ExoUtils and set the player`fun initializePlayer(video: Video): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [initLeaderboard](init-leaderboard.md) | getting data from the Leaderboard and Listening for updates`fun initLeaderboard(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [onConfigurationChanged](on-configuration-changed.md) | `fun onConfigurationChanged(newConfig: `[`Configuration`](https://developer.android.com/reference/android/content/res/Configuration.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [onCreateView](on-create-view.md) | `fun onCreateView(inflater: `[`LayoutInflater`](https://developer.android.com/reference/android/view/LayoutInflater.html)`, container: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`?, savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`View`](https://developer.android.com/reference/android/view/View.html)`?` |
| (JVM) [onIsPlayingChanged](on-is-playing-changed.md) | `fun onIsPlayingChanged(isPlaying: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [onLoadingChanged](on-loading-changed.md) | `fun onLoadingChanged(isLoading: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [onPause](on-pause.md) | `fun onPause(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [onPlayerError](on-player-error.md) | `fun onPlayerError(error: ExoPlaybackException?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [onPlayerStateChanged](on-player-state-changed.md) | `fun onPlayerStateChanged(playWhenReady: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, playbackState: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [onResume](on-resume.md) | `fun onResume(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [onStart](on-start.md) | `fun onStart(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [onStop](on-stop.md) | `fun onStop(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [onTracksChanged](on-tracks-changed.md) | `fun onTracksChanged(trackGroups: TrackGroupArray?, trackSelections: TrackSelectionArray?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [onViewCreated](on-view-created.md) | `fun onViewCreated(view: `[`View`](https://developer.android.com/reference/android/view/View.html)`, savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [releasePlayer](release-player.md) | release the player on Stop to avoid memory leaks`fun releasePlayer(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [setObservations](set-observations.md) | observe HeartRate, BluetoothState Live data from ViewModel`fun setObservations(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [setOnClicks](set-on-clicks.md) | sets OnClickListeners to Fullscreen, Stop Workout and Leaderboard Toggle`fun setOnClicks(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [setUI](set-u-i.md) | set the Ui elements Like title, difficulty, etc form the Video`fun setUI(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [showHR](show-h-r.md) | Show the Heart rate`fun showHR(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

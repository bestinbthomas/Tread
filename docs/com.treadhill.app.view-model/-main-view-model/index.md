[data](../../index.md) / [com.treadhill.app.viewModel](../index.md) / [MainViewModel](./index.md)

# MainViewModel

(JVM) `class MainViewModel : ViewModel, CoroutineScope`

### Constructors

| Name | Summary |
|---|---|
| (JVM) [&lt;init&gt;](-init-.md) | `MainViewModel(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`)` |

### Properties

| Name | Summary |
|---|---|
| (JVM) [allVideos](all-videos.md) | `var allVideos: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`VideoItem`](../../com.treadhill.app.data-types/-video-item/index.md)`>` |
| (JVM) [bTState](b-t-state.md) | `val bTState: LiveData<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| (JVM) [context](context.md) | `val context: `[`Context`](https://developer.android.com/reference/android/content/Context.html) |
| (JVM) [coroutineContext](coroutine-context.md) | `val coroutineContext: `[`CoroutineContext`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/-coroutine-context/index.html) |
| (JVM) [email](email.md) | `val email: LiveData<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?>` |
| (JVM) [filterOptions](filter-options.md) | `val filterOptions: LiveData<`[`FilterOptions`](../../com.treadhill.app.data-types/-filter-options/index.md)`>` |
| (JVM) [filterSearch](filter-search.md) | `val filterSearch: MutableLiveData<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| (JVM) [firebaseUtils](firebase-utils.md) | `val firebaseUtils: `[`FirebaseUtils`](../../com.treadhill.app.utilities/-firebase-utils/index.md) |
| (JVM) [heartRate](heart-rate.md) | `val heartRate: LiveData<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| (JVM) [mutableBTState](mutable-b-t-state.md) | `val mutableBTState: MutableLiveData<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| (JVM) [mutableEmail](mutable-email.md) | `val mutableEmail: MutableLiveData<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?>` |
| (JVM) [mutableFilterOptions](mutable-filter-options.md) | `val mutableFilterOptions: MutableLiveData<`[`FilterOptions`](../../com.treadhill.app.data-types/-filter-options/index.md)`!>` |
| (JVM) [mutableHeartRate](mutable-heart-rate.md) | `val mutableHeartRate: MutableLiveData<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| (JVM) [mutableNowPlayingVideo](mutable-now-playing-video.md) | `val mutableNowPlayingVideo: MutableLiveData<Video>` |
| (JVM) [mutableSelectedVideo](mutable-selected-video.md) | `val mutableSelectedVideo: MutableLiveData<Video>` |
| (JVM) [mutableTreadVideoList](mutable-tread-video-list.md) | `val mutableTreadVideoList: MutableLiveData<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`VideoItem`](../../com.treadhill.app.data-types/-video-item/index.md)`>>` |
| (JVM) [mutableVideoList](mutable-video-list.md) | `val mutableVideoList: MutableLiveData<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<Video>>` |
| (JVM) [mutableWeakInfo](mutable-weak-info.md) | `val mutableWeakInfo: MutableLiveData<`[`WeakInfo`](../../com.treadhill.app.data-types/-weak-info/index.md)`>` |
| (JVM) [mutableWeakWorkoutSummaries](mutable-weak-workout-summaries.md) | `val mutableWeakWorkoutSummaries: MutableLiveData<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`WorkoutSummary`](../../com.treadhill.app.data-types/-workout-summary/index.md)`>>` |
| (JVM) [nowPlayingVideo](now-playing-video.md) | `val nowPlayingVideo: LiveData<Video>` |
| (JVM) [scanBT](scan-b-t.md) | `val scanBT: MutableLiveData<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`>` |
| (JVM) [selectedVideo](selected-video.md) | `val selectedVideo: LiveData<Video>` |
| (JVM) [treadVideoList](tread-video-list.md) | `val treadVideoList: LiveData<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`VideoItem`](../../com.treadhill.app.data-types/-video-item/index.md)`>>` |
| (JVM) [videoList](video-list.md) | `val videoList: LiveData<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<Video>>` |
| (JVM) [vimeoUtils](vimeo-utils.md) | `val vimeoUtils: `[`VimeoUtils`](../../com.treadhill.app.utilities/-vimeo-utils/index.md) |
| (JVM) [weakInfo](weak-info.md) | `val weakInfo: LiveData<`[`WeakInfo`](../../com.treadhill.app.data-types/-weak-info/index.md)`>` |
| (JVM) [weakWorkoutSummaries](weak-workout-summaries.md) | `val weakWorkoutSummaries: LiveData<`[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`WorkoutSummary`](../../com.treadhill.app.data-types/-workout-summary/index.md)`>>` |

### Functions

| Name | Summary |
|---|---|
| (JVM) [filterVideos](filter-videos.md) | `fun filterVideos(options: `[`FilterOptions`](../../com.treadhill.app.data-types/-filter-options/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [getVimeoVideo](get-vimeo-video.md) | `fun getVimeoVideo(uri: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Job` |
| (JVM) [getWorkoutsInWeak](get-workouts-in-weak.md) | `fun getWorkoutsInWeak(date: `[`Calendar`](https://docs.oracle.com/javase/8/docs/api/java/util/Calendar.html)`): Job` |
| (JVM) [listen](listen.md) | handle commands from activity or fragment`fun listen(action: `[`Actions`](../-actions/index.md)`): `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html) |
| (JVM) [postHeartrate](post-heartrate.md) | `fun postHeartrate(workoutId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, value: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [postScore](post-score.md) | `fun postScore(workoutId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, score: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): Job` |
| (JVM) [postWorkoutSummary](post-workout-summary.md) | `fun postWorkoutSummary(workoutSummary: `[`WorkoutSummary`](../../com.treadhill.app.data-types/-workout-summary/index.md)`): Job` |

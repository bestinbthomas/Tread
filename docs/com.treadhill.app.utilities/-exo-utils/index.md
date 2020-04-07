[data](../../index.md) / [com.treadhill.app.utilities](../index.md) / [ExoUtils](./index.md)

# ExoUtils

(JVM) `class ExoUtils`

Utility Class for Exoplayer

### Constructors

| Name | Summary |
|---|---|
| (JVM) [&lt;init&gt;](-init-.md) | Utility Class for Exoplayer`ExoUtils(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`)` |

### Properties

| Name | Summary |
|---|---|
| (JVM) [context](context.md) | `val context: `[`Context`](https://developer.android.com/reference/android/content/Context.html) |
| (JVM) [QualityList](-quality-list.md) | `val QualityList: `[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |

### Functions

| Name | Summary |
|---|---|
| (JVM) [getMediaSource](get-media-source.md) | get media source for exoplayer`fun getMediaSource(video: Video): MediaSource?` |

### Companion Object Properties

| Name | Summary |
|---|---|
| (JVM) [exoUtils](exo-utils.md) | `var exoUtils: `[`ExoUtils`](./index.md)`?` |
| (JVM) [MAX_BUFFER_DURATION](-m-a-x_-b-u-f-f-e-r_-d-u-r-a-t-i-o-n.md) | `val MAX_BUFFER_DURATION: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| (JVM) [MIN_BUFFER_DURATION](-m-i-n_-b-u-f-f-e-r_-d-u-r-a-t-i-o-n.md) | `val MIN_BUFFER_DURATION: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| (JVM) [MIN_PLAYBACK_RESUME_BUFFER](-m-i-n_-p-l-a-y-b-a-c-k_-r-e-s-u-m-e_-b-u-f-f-e-r.md) | `val MIN_PLAYBACK_RESUME_BUFFER: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| (JVM) [MIN_PLAYBACK_START_BUFFER](-m-i-n_-p-l-a-y-b-a-c-k_-s-t-a-r-t_-b-u-f-f-e-r.md) | `val MIN_PLAYBACK_START_BUFFER: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

### Companion Object Functions

| Name | Summary |
|---|---|
| (JVM) [getLoadControl](get-load-control.md) | `fun getLoadControl(): DefaultLoadControl!` |
| (JVM) [getTrackSelector](get-track-selector.md) | `fun getTrackSelector(): DefaultTrackSelector` |

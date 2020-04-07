[data](../../index.md) / [com.treadhill.app.utilities](../index.md) / [VimeoUtils](./index.md)

# VimeoUtils

(JVM) `class VimeoUtils`

Utility class to get videos from vimeo

this Utility class uses API in https://github.com/vimeo/vimeo-networking-java library
refer that for more info

### Constructors

| Name | Summary |
|---|---|
| (JVM) [&lt;init&gt;](-init-.md) | `VimeoUtils(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`)` |

### Properties

| Name | Summary |
|---|---|
| (JVM) [cachedVideos](cached-videos.md) | `val cachedVideos: `[`HashMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-hash-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, Video>` |
| (JVM) [config](config.md) | `val config: Configuration!` |
| (JVM) [TOKEN](-t-o-k-e-n.md) | Vimeo API token it is secret do not publish`val TOKEN: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| (JVM) [vimoClient](vimo-client.md) | `val vimoClient: VimeoClient` |

### Functions

| Name | Summary |
|---|---|
| (JVM) [getMultipleVids](get-multiple-vids.md) | fetch Multiple videos from vimeo`suspend fun getMultipleVids(uri: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Result`](../../com.treadhill.app.data-types/-result/index.md)`<VideoList>` |
| (JVM) [getUser](get-user.md) | get the Vimeo User`suspend fun getUser(): `[`Result`](../../com.treadhill.app.data-types/-result/index.md)`<User>` |
| (JVM) [getUserVids](get-user-vids.md) | get all videos uploaded bu the user that is all the videos uploaded to vimeo account`suspend fun getUserVids(): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<Video>` |
| (JVM) [getVideo](get-video.md) | fetch video info from vimeo`suspend fun getVideo(uri: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Result`](../../com.treadhill.app.data-types/-result/index.md)`<Video>` |

### Companion Object Properties

| Name | Summary |
|---|---|
| (JVM) [instance](instance.md) | `var instance: `[`VimeoUtils`](./index.md)`?` |

### Companion Object Functions

| Name | Summary |
|---|---|
| (JVM) [getDashUrlFromVideo](get-dash-url-from-video.md) | get dash url from the video file`fun getDashUrlFromVideo(video: Video): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| (JVM) [getHlsUrlFromVideo](get-hls-url-from-video.md) | get hls url from the video file`fun getHlsUrlFromVideo(video: Video): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| (JVM) [getInstance](get-instance.md) | get the util instance`fun getInstance(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`): `[`VimeoUtils`](./index.md) |
| (JVM) [getProgressiveUrlsFromVideo](get-progressive-urls-from-video.md) | get MP4 url from the video file`fun getProgressiveUrlsFromVideo(video: Video): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`!>?` |

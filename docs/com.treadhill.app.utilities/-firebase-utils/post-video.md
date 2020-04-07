[data](../../index.md) / [com.treadhill.app.utilities](../index.md) / [FirebaseUtils](index.md) / [postVideo](./post-video.md)

# postVideo

(JVM) `fun postVideo(videoItems: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`VideoItem`](../../com.treadhill.app.data-types/-video-item/index.md)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

posts video to firestore

PATH : firestore.collection("videos").document(video.name).set(video)

### Parameters

`videoItems` - 
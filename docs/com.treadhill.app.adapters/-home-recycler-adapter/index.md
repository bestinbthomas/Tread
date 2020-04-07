[data](../../index.md) / [com.treadhill.app.adapters](../index.md) / [HomeRecyclerAdapter](./index.md)

# HomeRecyclerAdapter

(JVM) `class HomeRecyclerAdapter : Adapter<MyHolder>, `[`Filterable`](https://developer.android.com/reference/android/widget/Filterable.html)

### Types

| Name | Summary |
|---|---|
| (JVM) [MyHolder](-my-holder/index.md) | `class MyHolder : ViewHolder` |

### Constructors

| Name | Summary |
|---|---|
| (JVM) [&lt;init&gt;](-init-.md) | `HomeRecyclerAdapter(videoThumbnails: `[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`VideoThumb`](../../com.treadhill.app.data-types/-video-thumb/index.md)`>, clickListner: MutableLiveData<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`> = MutableLiveData())` |

### Properties

| Name | Summary |
|---|---|
| (JVM) [clickListner](click-listner.md) | `val clickListner: MutableLiveData<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| (JVM) [fullVideoThumbnails](full-video-thumbnails.md) | `val fullVideoThumbnails: `[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`VideoThumb`](../../com.treadhill.app.data-types/-video-thumb/index.md)`>` |
| (JVM) [videoThumbnails](video-thumbnails.md) | `var videoThumbnails: `[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`VideoThumb`](../../com.treadhill.app.data-types/-video-thumb/index.md)`>` |

### Functions

| Name | Summary |
|---|---|
| (JVM) [getFilter](get-filter.md) | `fun getFilter(): `[`Filter`](https://developer.android.com/reference/android/widget/Filter.html) |
| (JVM) [getItemCount](get-item-count.md) | `fun getItemCount(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| (JVM) [onBindViewHolder](on-bind-view-holder.md) | `fun onBindViewHolder(holder: MyHolder, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [onCreateViewHolder](on-create-view-holder.md) | `fun onCreateViewHolder(parent: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`, viewType: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): MyHolder` |

[data](../../index.md) / [com.treadhill.app.adapters](../index.md) / [CategoryRecyclerAdapter](./index.md)

# CategoryRecyclerAdapter

(JVM) `class CategoryRecyclerAdapter : Adapter<MyHolder>`

### Types

| Name | Summary |
|---|---|
| (JVM) [MyHolder](-my-holder/index.md) | `class MyHolder : ViewHolder` |

### Constructors

| Name | Summary |
|---|---|
| (JVM) [&lt;init&gt;](-init-.md) | `CategoryRecyclerAdapter(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`, videoItems: `[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`VideoItem`](../../com.treadhill.app.data-types/-video-item/index.md)`>, clickListner: MutableLiveData<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`> = MutableLiveData())` |

### Properties

| Name | Summary |
|---|---|
| (JVM) [clickListner](click-listner.md) | `val clickListner: MutableLiveData<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| (JVM) [context](context.md) | `val context: `[`Context`](https://developer.android.com/reference/android/content/Context.html) |
| (JVM) [videoItems](video-items.md) | `var videoItems: `[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`VideoItem`](../../com.treadhill.app.data-types/-video-item/index.md)`>` |

### Functions

| Name | Summary |
|---|---|
| (JVM) [getItemCount](get-item-count.md) | `fun getItemCount(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| (JVM) [onBindViewHolder](on-bind-view-holder.md) | `fun onBindViewHolder(holder: MyHolder, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [onCreateViewHolder](on-create-view-holder.md) | `fun onCreateViewHolder(parent: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`, viewType: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): MyHolder` |
| (JVM) [submitList](submit-list.md) | `fun submitList(videos: `[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`VideoItem`](../../com.treadhill.app.data-types/-video-item/index.md)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

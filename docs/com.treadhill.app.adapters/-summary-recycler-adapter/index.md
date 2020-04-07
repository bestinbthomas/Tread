[data](../../index.md) / [com.treadhill.app.adapters](../index.md) / [SummaryRecyclerAdapter](./index.md)

# SummaryRecyclerAdapter

(JVM) `class SummaryRecyclerAdapter : ListAdapter<`[`WorkoutSummary`](../../com.treadhill.app.data-types/-workout-summary/index.md)`, SummaryHolder>`

### Types

| Name | Summary |
|---|---|
| (JVM) [SummaryHolder](-summary-holder/index.md) | `class SummaryHolder : ViewHolder` |

### Constructors

| Name | Summary |
|---|---|
| (JVM) [&lt;init&gt;](-init-.md) | `SummaryRecyclerAdapter(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`)` |

### Properties

| Name | Summary |
|---|---|
| (JVM) [clickListener](click-listener.md) | `val clickListener: MutableLiveData<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| (JVM) [context](context.md) | `val context: `[`Context`](https://developer.android.com/reference/android/content/Context.html) |

### Functions

| Name | Summary |
|---|---|
| (JVM) [onBindViewHolder](on-bind-view-holder.md) | `fun onBindViewHolder(holder: SummaryHolder, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [onCreateViewHolder](on-create-view-holder.md) | `fun onCreateViewHolder(parent: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`, viewType: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): SummaryHolder` |

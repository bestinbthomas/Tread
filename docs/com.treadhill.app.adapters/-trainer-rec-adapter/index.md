[data](../../index.md) / [com.treadhill.app.adapters](../index.md) / [TrainerRecAdapter](./index.md)

# TrainerRecAdapter

(JVM) `class TrainerRecAdapter : Adapter<Holder>`

### Types

| Name | Summary |
|---|---|
| (JVM) [Holder](-holder/index.md) | `class Holder : ViewHolder` |

### Constructors

| Name | Summary |
|---|---|
| (JVM) [&lt;init&gt;](-init-.md) | `TrainerRecAdapter(trainerList: `[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`Trainer`](../../com.treadhill.app.data-types/-trainer/index.md)`>, clickListener: MutableLiveData<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`> = MutableLiveData())` |

### Properties

| Name | Summary |
|---|---|
| (JVM) [clickListener](click-listener.md) | `val clickListener: MutableLiveData<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| (JVM) [trainerList](trainer-list.md) | `val trainerList: `[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`Trainer`](../../com.treadhill.app.data-types/-trainer/index.md)`>` |

### Functions

| Name | Summary |
|---|---|
| (JVM) [getItemCount](get-item-count.md) | `fun getItemCount(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| (JVM) [onBindViewHolder](on-bind-view-holder.md) | `fun onBindViewHolder(holder: Holder, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [onCreateViewHolder](on-create-view-holder.md) | `fun onCreateViewHolder(parent: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`, viewType: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): Holder` |

[data](../../index.md) / [com.treadhill.app.adapters](../index.md) / [TextRecAdapter](./index.md)

# TextRecAdapter

(JVM) `class TextRecAdapter : Adapter<Holder>`

### Types

| Name | Summary |
|---|---|
| (JVM) [Holder](-holder/index.md) | `class Holder : ViewHolder` |

### Constructors

| Name | Summary |
|---|---|
| (JVM) [&lt;init&gt;](-init-.md) | `TextRecAdapter(texts: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>, context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`)` |

### Properties

| Name | Summary |
|---|---|
| (JVM) [context](context.md) | `val context: `[`Context`](https://developer.android.com/reference/android/content/Context.html) |
| (JVM) [selectedItems](selected-items.md) | `var selectedItems: `[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| (JVM) [texts](texts.md) | `val texts: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |

### Functions

| Name | Summary |
|---|---|
| (JVM) [getItemCount](get-item-count.md) | `fun getItemCount(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| (JVM) [onBindViewHolder](on-bind-view-holder.md) | `fun onBindViewHolder(holder: Holder, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [onCreateViewHolder](on-create-view-holder.md) | `fun onCreateViewHolder(parent: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`, viewType: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): Holder` |
| (JVM) [setSelectedList](set-selected-list.md) | `fun setSelectedList(list: `[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

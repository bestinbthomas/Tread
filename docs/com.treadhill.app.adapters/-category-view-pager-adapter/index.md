[data](../../index.md) / [com.treadhill.app.adapters](../index.md) / [CategoryViewPagerAdapter](./index.md)

# CategoryViewPagerAdapter

(JVM) `class CategoryViewPagerAdapter : PagerAdapter`

### Constructors

| Name | Summary |
|---|---|
| (JVM) [&lt;init&gt;](-init-.md) | `CategoryViewPagerAdapter(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`, owner: LifecycleOwner, categories: `[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`Category`](../../com.treadhill.app.data-types/-category/index.md)`>, clickListener: MutableLiveData<`[`Pair`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>> = MutableLiveData())` |

### Properties

| Name | Summary |
|---|---|
| (JVM) [categories](categories.md) | `var categories: `[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`Category`](../../com.treadhill.app.data-types/-category/index.md)`>` |
| (JVM) [clickListener](click-listener.md) | `val clickListener: MutableLiveData<`[`Pair`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>>` |
| (JVM) [context](context.md) | `val context: `[`Context`](https://developer.android.com/reference/android/content/Context.html) |
| (JVM) [owner](owner.md) | `val owner: LifecycleOwner` |

### Functions

| Name | Summary |
|---|---|
| (JVM) [destroyItem](destroy-item.md) | `fun destroyItem(container: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, object: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [getCount](get-count.md) | `fun getCount(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| (JVM) [getPageTitle](get-page-title.md) | `fun getPageTitle(position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`CharSequence`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char-sequence/index.html)`?` |
| (JVM) [instantiateItem](instantiate-item.md) | `fun instantiateItem(container: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`View`](https://developer.android.com/reference/android/view/View.html) |
| (JVM) [isViewFromObject](is-view-from-object.md) | `fun isViewFromObject(view: `[`View`](https://developer.android.com/reference/android/view/View.html)`, object: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| (JVM) [updateList](update-list.md) | `fun updateList(categories: `[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`Category`](../../com.treadhill.app.data-types/-category/index.md)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

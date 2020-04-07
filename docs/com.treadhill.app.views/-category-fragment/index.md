[data](../../index.md) / [com.treadhill.app.views](../index.md) / [CategoryFragment](./index.md)

# CategoryFragment

(JVM) `class CategoryFragment : Fragment`

### Constructors

| Name | Summary |
|---|---|
| (JVM) [&lt;init&gt;](-init-.md) | `CategoryFragment()` |

### Properties

| Name | Summary |
|---|---|
| (JVM) [adapter](adapter.md) | `lateinit var adapter: `[`CategoryViewPagerAdapter`](../../com.treadhill.app.adapters/-category-view-pager-adapter/index.md) |
| (JVM) [categories](categories.md) | `var categories: `[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`Category`](../../com.treadhill.app.data-types/-category/index.md)`>` |
| (JVM) [mView](m-view.md) | `lateinit var mView: `[`View`](https://developer.android.com/reference/android/view/View.html) |
| (JVM) [viewModel](view-model.md) | `lateinit var viewModel: `[`MainViewModel`](../../com.treadhill.app.view-model/-main-view-model/index.md) |

### Functions

| Name | Summary |
|---|---|
| (JVM) [onActivityCreated](on-activity-created.md) | `fun onActivityCreated(savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [onCreateView](on-create-view.md) | `fun onCreateView(inflater: `[`LayoutInflater`](https://developer.android.com/reference/android/view/LayoutInflater.html)`, container: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`?, savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`View`](https://developer.android.com/reference/android/view/View.html)`?` |
| (JVM) [onStart](on-start.md) | `fun onStart(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [onViewCreated](on-view-created.md) | `fun onViewCreated(view: `[`View`](https://developer.android.com/reference/android/view/View.html)`, savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [populateCategories](populate-categories.md) | segregate the videos into categories`fun populateCategories(videos: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`VideoItem`](../../com.treadhill.app.data-types/-video-item/index.md)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [setupObservations](setup-observations.md) | observe Live data from view model - filertSearch and Video Lst`fun setupObservations(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [setupViewPager](setup-view-pager.md) | The Categories are implemented as Viewpager and it is initialised here`fun setupViewPager(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Companion Object Functions

| Name | Summary |
|---|---|
| (JVM) [newInstance](new-instance.md) | `fun newInstance(): `[`CategoryFragment`](./index.md) |

[data](../../index.md) / [com.treadhill.app.views](../index.md) / [DashboardFragment](./index.md)

# DashboardFragment

(JVM) `class DashboardFragment : Fragment`

### Constructors

| Name | Summary |
|---|---|
| (JVM) [&lt;init&gt;](-init-.md) | `DashboardFragment()` |

### Properties

| Name | Summary |
|---|---|
| (JVM) [adapter](adapter.md) | `lateinit var adapter: `[`SummaryRecyclerAdapter`](../../com.treadhill.app.adapters/-summary-recycler-adapter/index.md) |
| (JVM) [date](date.md) | `val date: `[`Calendar`](https://docs.oracle.com/javase/8/docs/api/java/util/Calendar.html)`!` |
| (JVM) [mView](m-view.md) | `lateinit var mView: `[`View`](https://developer.android.com/reference/android/view/View.html) |
| (JVM) [spinerPosition](spiner-position.md) | `var spinerPosition: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| (JVM) [viewModel](view-model.md) | `lateinit var viewModel: `[`MainViewModel`](../../com.treadhill.app.view-model/-main-view-model/index.md) |
| (JVM) [weakInfo](weak-info.md) | `var weakInfo: `[`WeakInfo`](../../com.treadhill.app.data-types/-weak-info/index.md) |

### Functions

| Name | Summary |
|---|---|
| (JVM) [checkSpinner](check-spinner.md) | handles different options selected by the spinner - scores, calories and duration`fun checkSpinner(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [onActivityCreated](on-activity-created.md) | `fun onActivityCreated(savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [onCreateView](on-create-view.md) | `fun onCreateView(inflater: `[`LayoutInflater`](https://developer.android.com/reference/android/view/LayoutInflater.html)`, container: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`?, savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`View`](https://developer.android.com/reference/android/view/View.html)`?` |
| (JVM) [onStart](on-start.md) | `fun onStart(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [onViewCreated](on-view-created.md) | `fun onViewCreated(view: `[`View`](https://developer.android.com/reference/android/view/View.html)`, savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [setDate](set-date.md) | sets date below graph`fun setDate(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [setObservations](set-observations.md) | Observes Weak metadata and List of summaries from viewmodel`fun setObservations(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [setOnClicks](set-on-clicks.md) | handle next and previous week button clicks`fun setOnClicks(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [setupGraph](setup-graph.md) | Presents the WeakMetadata in graph using MPAndroidChart`fun setupGraph(weakInfo: `[`WeakInfo`](../../com.treadhill.app.data-types/-weak-info/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [setupRecycler](setup-recycler.md) | attaches adapter to the list of video recycler view`fun setupRecycler(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Companion Object Functions

| Name | Summary |
|---|---|
| (JVM) [newInstance](new-instance.md) | `fun newInstance(): `[`DashboardFragment`](./index.md) |

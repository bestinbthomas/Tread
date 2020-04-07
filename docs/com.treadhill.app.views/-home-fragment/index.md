[data](../../index.md) / [com.treadhill.app.views](../index.md) / [HomeFragment](./index.md)

# HomeFragment

(JVM) `class HomeFragment : Fragment`

### Constructors

| Name | Summary |
|---|---|
| (JVM) [&lt;init&gt;](-init-.md) | `HomeFragment()` |

### Properties

| Name | Summary |
|---|---|
| (JVM) [adapter1](adapter1.md) | `val adapter1: `[`HomeRecyclerAdapter`](../../com.treadhill.app.adapters/-home-recycler-adapter/index.md) |
| (JVM) [counter](counter.md) | Moves the Moving Carousel every 2 seconds`val counter: <no name provided>` |
| (JVM) [ItemList1](-item-list1.md) | `val ItemList1: `[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`VideoThumb`](../../com.treadhill.app.data-types/-video-thumb/index.md)`>` |
| (JVM) [mView](m-view.md) | `lateinit var mView: `[`View`](https://developer.android.com/reference/android/view/View.html) |
| (JVM) [videolist](videolist.md) | `var videolist: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`VideoItem`](../../com.treadhill.app.data-types/-video-item/index.md)`>?` |
| (JVM) [viewModel](view-model.md) | `lateinit var viewModel: `[`MainViewModel`](../../com.treadhill.app.view-model/-main-view-model/index.md) |

### Functions

| Name | Summary |
|---|---|
| (JVM) [observeViewModel](observe-view-model.md) | observing the ViewModel Live datas - HeartRate, bluetooth state, Video List`fun observeViewModel(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [onActivityCreated](on-activity-created.md) | `fun onActivityCreated(savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [onCreateView](on-create-view.md) | `fun onCreateView(inflater: `[`LayoutInflater`](https://developer.android.com/reference/android/view/LayoutInflater.html)`, container: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`?, savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`View`](https://developer.android.com/reference/android/view/View.html)`?` |
| (JVM) [onStart](on-start.md) | `fun onStart(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [onStop](on-stop.md) | `fun onStop(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [onViewCreated](on-view-created.md) | `fun onViewCreated(view: `[`View`](https://developer.android.com/reference/android/view/View.html)`, savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [setupCal](setup-cal.md) | Set Up The Calender`fun setupCal(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [setupRecyclers](setup-recyclers.md) | Sets adapters for Moving Carousel, Horizontal Videos List and Horizontal Trainers List`fun setupRecyclers(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [startCounter](start-counter.md) | `fun startCounter(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

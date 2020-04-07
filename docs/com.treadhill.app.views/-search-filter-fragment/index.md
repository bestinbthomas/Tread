[data](../../index.md) / [com.treadhill.app.views](../index.md) / [SearchFilterFragment](./index.md)

# SearchFilterFragment

(JVM) `class SearchFilterFragment : Fragment`

Filter Page with options to filter the Videos

### Constructors

| Name | Summary |
|---|---|
| (JVM) [&lt;init&gt;](-init-.md) | Filter Page with options to filter the Videos`SearchFilterFragment()` |

### Properties

| Name | Summary |
|---|---|
| (JVM) [difficultyAdapter](difficulty-adapter.md) | `lateinit var difficultyAdapter: `[`TextRecAdapter`](../../com.treadhill.app.adapters/-text-rec-adapter/index.md) |
| (JVM) [mView](m-view.md) | `lateinit var mView: `[`View`](https://developer.android.com/reference/android/view/View.html) |
| (JVM) [options](options.md) | `var options: `[`FilterOptions`](../../com.treadhill.app.data-types/-filter-options/index.md) |
| (JVM) [typeAdapter](type-adapter.md) | `lateinit var typeAdapter: `[`TextRecAdapter`](../../com.treadhill.app.adapters/-text-rec-adapter/index.md) |
| (JVM) [viewModel](view-model.md) | `lateinit var viewModel: `[`MainViewModel`](../../com.treadhill.app.view-model/-main-view-model/index.md) |

### Functions

| Name | Summary |
|---|---|
| (JVM) [onCreateView](on-create-view.md) | `fun onCreateView(inflater: `[`LayoutInflater`](https://developer.android.com/reference/android/view/LayoutInflater.html)`, container: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`?, savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`View`](https://developer.android.com/reference/android/view/View.html)`?` |
| (JVM) [onStart](on-start.md) | `fun onStart(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [onViewCreated](on-view-created.md) | `fun onViewCreated(view: `[`View`](https://developer.android.com/reference/android/view/View.html)`, savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [setClickliseners](set-clickliseners.md) | Handle Clicks on back button, Filter button, and reset button`fun setClickliseners(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [seteditText](setedit-text.md) | Sets the search Field to enter the search data`fun seteditText(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [setObservations](set-observations.md) | observe filterOptions live data from ViewModels`fun setObservations(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [setupRecycles](setup-recycles.md) | attach adapters to Type, Difficulty, Trainers Recycler view`fun setupRecycles(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [setUpSlider](set-up-slider.md) | Sets up the Duration Slider to select the duration`fun setUpSlider(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

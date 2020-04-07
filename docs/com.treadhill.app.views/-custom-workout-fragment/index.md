[data](../../index.md) / [com.treadhill.app.views](../index.md) / [CustomWorkoutFragment](./index.md)

# CustomWorkoutFragment

(JVM) `class CustomWorkoutFragment : Fragment`

### Constructors

| Name | Summary |
|---|---|
| (JVM) [&lt;init&gt;](-init-.md) | `CustomWorkoutFragment()` |

### Properties

| Name | Summary |
|---|---|
| (JVM) [avgHeartRate](avg-heart-rate.md) | `val avgHeartRate: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| (JVM) [count](count.md) | `var count: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| (JVM) [heartRate](heart-rate.md) | `var heartRate: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| (JVM) [isTimerRunning](is-timer-running.md) | `var isTimerRunning: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| (JVM) [mView](m-view.md) | `lateinit var mView: `[`View`](https://developer.android.com/reference/android/view/View.html) |
| (JVM) [pauseOffset](pause-offset.md) | `var pauseOffset: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| (JVM) [totalHR](total-h-r.md) | `var totalHR: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| (JVM) [viewModel](view-model.md) | `lateinit var viewModel: `[`MainViewModel`](../../com.treadhill.app.view-model/-main-view-model/index.md) |
| (JVM) [workoutId](workout-id.md) | `var workoutId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| (JVM) [workoutRecorder](workout-recorder.md) | `lateinit var workoutRecorder: `[`WorkoutRecorder`](../../com.treadhill.app.utilities/-workout-recorder/index.md) |

### Functions

| Name | Summary |
|---|---|
| (JVM) [onActivityCreated](on-activity-created.md) | `fun onActivityCreated(savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [onCreateView](on-create-view.md) | `fun onCreateView(inflater: `[`LayoutInflater`](https://developer.android.com/reference/android/view/LayoutInflater.html)`, container: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`?, savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`View`](https://developer.android.com/reference/android/view/View.html)`?` |
| (JVM) [onStart](on-start.md) | `fun onStart(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [onViewCreated](on-view-created.md) | `fun onViewCreated(view: `[`View`](https://developer.android.com/reference/android/view/View.html)`, savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [resetTimer](reset-timer.md) | reset the timer`fun resetTimer(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [setHeartRate](set-heart-rate.md) | update the UI after recieving the HR calls checkZoneChange(this.heartRate) on WorkoutRecorder, gets calories from recorder`fun setHeartRate(heartRate: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [setObservations](set-observations.md) | observe Heart rate Live data from ViewModel`fun setObservations(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [setupSlider](setup-slider.md) | disable Target zone on slider`fun setupSlider(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [startStopTimer](start-stop-timer.md) | start, pause and resume the workout`fun startStopTimer(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [stopWorkout](stop-workout.md) | Finishes the workout, get summary from WorkoutRecorder and navigate to Summary page`fun stopWorkout(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

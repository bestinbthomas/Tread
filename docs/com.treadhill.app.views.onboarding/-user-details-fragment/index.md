[data](../../index.md) / [com.treadhill.app.views.onboarding](../index.md) / [UserDetailsFragment](./index.md)

# UserDetailsFragment

(JVM) `class UserDetailsFragment : Fragment`

### Constructors

| Name | Summary |
|---|---|
| (JVM) [&lt;init&gt;](-init-.md) | `UserDetailsFragment()` |

### Properties

| Name | Summary |
|---|---|
| (JVM) [dob](dob.md) | `val dob: `[`Calendar`](https://docs.oracle.com/javase/8/docs/api/java/util/Calendar.html)`!` |
| (JVM) [mView](m-view.md) | `lateinit var mView: `[`View`](https://developer.android.com/reference/android/view/View.html) |

### Functions

| Name | Summary |
|---|---|
| (JVM) [addDataToSharedPref](add-data-to-shared-pref.md) | add the user to shared Preference`fun addDataToSharedPref(user: `[`User`](../../com.treadhill.app.data-types/-user/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [createUser](create-user.md) | create the User object from the data entered`fun createUser(): `[`User`](../../com.treadhill.app.data-types/-user/index.md) |
| (JVM) [onCreateView](on-create-view.md) | `fun onCreateView(inflater: `[`LayoutInflater`](https://developer.android.com/reference/android/view/LayoutInflater.html)`, container: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`?, savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`View`](https://developer.android.com/reference/android/view/View.html)`?` |
| (JVM) [onStart](on-start.md) | `fun onStart(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [onViewCreated](on-view-created.md) | `fun onViewCreated(view: `[`View`](https://developer.android.com/reference/android/view/View.html)`, savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [validateInput](validate-input.md) | TODO validate the given input`fun validateInput(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

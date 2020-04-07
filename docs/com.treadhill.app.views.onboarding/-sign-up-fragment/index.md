[data](../../index.md) / [com.treadhill.app.views.onboarding](../index.md) / [SignUpFragment](./index.md)

# SignUpFragment

(JVM) `class SignUpFragment : Fragment`

A simple [Fragment](#) subclass.

### Constructors

| Name | Summary |
|---|---|
| (JVM) [&lt;init&gt;](-init-.md) | A simple [Fragment](#) subclass.`SignUpFragment()` |

### Properties

| Name | Summary |
|---|---|
| (JVM) [confirmPasswordInputLayout](confirm-password-input-layout.md) | `lateinit var confirmPasswordInputLayout: TextInputLayout` |
| (JVM) [emailPhoneInputLayout](email-phone-input-layout.md) | `lateinit var emailPhoneInputLayout: TextInputLayout` |
| (JVM) [isEmail](is-email.md) | `var isEmail: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| (JVM) [mAuth](m-auth.md) | `var mAuth: FirebaseAuth` |
| (JVM) [mView](m-view.md) | `lateinit var mView: `[`View`](https://developer.android.com/reference/android/view/View.html) |
| (JVM) [passwordInputLayout](password-input-layout.md) | `lateinit var passwordInputLayout: TextInputLayout` |

### Functions

| Name | Summary |
|---|---|
| (JVM) [emailSelected](email-selected.md) | update view for signing up with email`fun emailSelected(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [onCreateView](on-create-view.md) | `fun onCreateView(inflater: `[`LayoutInflater`](https://developer.android.com/reference/android/view/LayoutInflater.html)`, container: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`?, savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`View`](https://developer.android.com/reference/android/view/View.html)`?` |
| (JVM) [onStart](on-start.md) | `fun onStart(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [onViewCreated](on-view-created.md) | `fun onViewCreated(view: `[`View`](https://developer.android.com/reference/android/view/View.html)`, savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [phoneSelected](phone-selected.md) | update view for signing up with phone number`fun phoneSelected(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [signUpWithEmailPassword](sign-up-with-email-password.md) | Firebase Sign up with Email and Password`fun signUpWithEmailPassword(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [signUpWithPhone](sign-up-with-phone.md) | navigate to verifyPhoneFragment to verify phone`fun signUpWithPhone(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [updateUI](update-u-i.md) | navigate to MainActivity if Login successful (user not null)`fun updateUI(user: FirebaseUser?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [validateInput](validate-input.md) | checks the input fields and validates the input`fun validateInput(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

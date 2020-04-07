[data](../../index.md) / [com.treadhill.app.views.onboarding](../index.md) / [VerifyPhoneFragment](./index.md)

# VerifyPhoneFragment

(JVM) `class VerifyPhoneFragment : Fragment`

Fragment to check the OTP sent to the user

### Constructors

| Name | Summary |
|---|---|
| (JVM) [&lt;init&gt;](-init-.md) | Fragment to check the OTP sent to the user`VerifyPhoneFragment()` |

### Properties

| Name | Summary |
|---|---|
| (JVM) [callbacks](callbacks.md) | `lateinit var callbacks: OnVerificationStateChangedCallbacks` |
| (JVM) [code](code.md) | `lateinit var code: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| (JVM) [codeSent](code-sent.md) | `var codeSent: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| (JVM) [mAuth](m-auth.md) | `val mAuth: FirebaseAuth` |
| (JVM) [mView](m-view.md) | `lateinit var mView: `[`View`](https://developer.android.com/reference/android/view/View.html) |
| (JVM) [resendToken](resend-token.md) | `lateinit var resendToken: ForceResendingToken` |
| (JVM) [storedVerificationId](stored-verification-id.md) | `lateinit var storedVerificationId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Functions

| Name | Summary |
|---|---|
| (JVM) [logInWithPhone](log-in-with-phone.md) | request OTP from firebase`fun logInWithPhone(phoneNumber: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [onCreateView](on-create-view.md) | `fun onCreateView(inflater: `[`LayoutInflater`](https://developer.android.com/reference/android/view/LayoutInflater.html)`, container: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`?, savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`View`](https://developer.android.com/reference/android/view/View.html)`?` |
| (JVM) [onStart](on-start.md) | `fun onStart(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [onViewCreated](on-view-created.md) | `fun onViewCreated(view: `[`View`](https://developer.android.com/reference/android/view/View.html)`, savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [signInWithPhoneAuthCredential](sign-in-with-phone-auth-credential.md) | sigin in with the credentials`fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

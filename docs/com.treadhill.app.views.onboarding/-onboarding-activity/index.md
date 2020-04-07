[data](../../index.md) / [com.treadhill.app.views.onboarding](../index.md) / [OnboardingActivity](./index.md)

# OnboardingActivity

(JVM) `class OnboardingActivity : AppCompatActivity`

### Constructors

| Name | Summary |
|---|---|
| (JVM) [&lt;init&gt;](-init-.md) | `OnboardingActivity()` |

### Functions

| Name | Summary |
|---|---|
| (JVM) [firebaseAuthWithGoogle](firebase-auth-with-google.md) | Login with Google`fun firebaseAuthWithGoogle(account: GoogleSignInAccount?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [onActivityResult](on-activity-result.md) | `fun onActivityResult(requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, resultCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, data: `[`Intent`](https://developer.android.com/reference/android/content/Intent.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [onCreate](on-create.md) | `fun onCreate(savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [updateUI](update-u-i.md) | navigate to main activity if login sucessful`fun updateUI(user: FirebaseUser?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

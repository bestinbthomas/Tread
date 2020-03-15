package com.treadhill.app.views.onboarding

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.treadhill.app.OnboardingNavGraphXmlDirections
import com.treadhill.app.R
import com.treadhill.app.highOrder.RC_SIGN_IN
import com.treadhill.app.highOrder.showSnackbar


class OnboardingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_0nboarding)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val navController = findNavController(R.id.onboarding_host_frag)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            try { // Google Sign In was successful, authenticate with Firebase
                val account: GoogleSignInAccount? = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account)
            } catch (e: ApiException) { // Google Sign In failed, update UI appropriately
                Log.w("Auth", "Google sign in failed", e)
                Toast.makeText(this, "Google sign in failed.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount?) {
        Log.d("Google Auth", "firebaseAuthWithGoogle:" + account?.id!!)

        val auth = FirebaseAuth.getInstance()
        val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("GOOGLE AUTH", "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("GOOGLE AUTH", "signInWithCredential:failure", task.exception)
                    showSnackbar("Authentication Failed.")
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        user?.let {
            Log.i("GoogleSignIn", "uid ${user.uid}")
            Log.i("GOOGLE SIGN IN", "current user ${FirebaseAuth.getInstance().currentUser?.uid}")
            findNavController(R.id.onboarding_host_frag).navigate(OnboardingNavGraphXmlDirections.actionGlobalCheckUserOnboardingDoneFragment(user.uid))
            return@updateUI
        }

        Toast.makeText(this, "we coundn't sign you in please try again", Toast.LENGTH_SHORT).show()
    }
}

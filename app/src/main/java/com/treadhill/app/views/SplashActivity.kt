package com.treadhill.app.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import com.treadhill.app.views.onboarding.OnboardingActivity

class SplashActivity : AppCompatActivity() {

    private val TAG = "SPLASH"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "Splash created")

    }

    override fun onStart() {
        super.onStart()
        val firebaseApp = Firebase.app
        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            Log.i(TAG, "User Logged in with ${auth.currentUser?.email}")
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            Log.i(TAG, "User Logged in with ${auth.currentUser?.email}")
            startActivity(Intent(this, OnboardingActivity::class.java))
        }
    }


}

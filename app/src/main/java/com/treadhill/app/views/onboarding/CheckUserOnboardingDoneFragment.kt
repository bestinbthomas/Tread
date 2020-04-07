package com.treadhill.app.views.onboarding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.firestore.FirebaseFirestore
import com.treadhill.app.R
import com.treadhill.app.dataTypes.User
import com.treadhill.app.highOrder.*
import com.treadhill.app.views.MainActivity

/**
 * Dummy Fragment to check if Onboarding is done in firebase
 *
 */
class CheckUserOnboardingDoneFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.check_user_data_exist, container, false)
    }

    override fun onStart() {
        super.onStart()

        val uid = navArgs<CheckUserOnboardingDoneFragmentArgs>().value.uid

        FirebaseFirestore.getInstance().collection("users").document(uid)
            .get().addOnSuccessListener {
                if (it.exists()) {
                    val user = it.toObject(User::class.java)

                    //store the details in shared pref as well
                    val sharedPreferences = requireActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putString(PREF_NAME, user!!.name)
                    editor.putString(PREF_GENDER, user.gender)
                    editor.putLong(PREF_DOB, user.dobMillis)
                    editor.putInt(PREF_HEIGHT, user.height)
                    editor.putInt(PREF_WEIGHT, user.weight)
                    editor.putInt(PREF_FREQUENCY, when (user.frequencyOfExercise) {
                        "No, I hardly exercise" -> 0
                        "1-3 days a week" -> 1
                        "3-5 days a week" -> 2
                        "Almost everyday" -> 3
                        else -> -1
                    })

                    editor.putInt(PREF_FITNESS_LEVEL, when (user.fitnessLevel) {
                        "Beginner" -> 0
                        "Intermediate" -> 1
                        "Pro" -> 2
                        else -> -1
                    })

                    editor.apply()
                    val intent = Intent(requireActivity().application, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    requireActivity().startActivity(intent)
                } else {
                    findNavController().navigate(CheckUserOnboardingDoneFragmentDirections.actionCheckUserOnboardingDoneFragmentToUserDetailsFragment())
                }
            }.addOnFailureListener {
                showSnackbar("something went wrong")
            }

    }
}
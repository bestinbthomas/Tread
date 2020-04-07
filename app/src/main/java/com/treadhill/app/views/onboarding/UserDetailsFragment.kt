package com.treadhill.app.views.onboarding


import android.app.DatePickerDialog
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.treadhill.app.R
import com.treadhill.app.dataTypes.User
import com.treadhill.app.highOrder.*
import com.treadhill.app.views.MainActivity
import kotlinx.android.synthetic.main.fragment_user_details.view.*
import java.util.*

class UserDetailsFragment : Fragment() {

    lateinit var mView: View
    val dob = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mView = view
    }

    override fun onStart() {
        super.onStart()

        mView.complete_btn.setOnClickListener {
            if (validateInput()) {
                val user = createUser()

                FirebaseAuth.getInstance().currentUser?.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(mView.name_il.editText!!.text.toString()).build())
                /**
                  * add the user to firebase
                  * */
                FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().currentUser?.uid
                    ?: "").set(user)

                addDataToSharedPref(user)

                val intent = Intent(requireActivity().application, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                requireActivity().startActivity(intent)
            }
        }

        mView.dob_picker_btn.setOnClickListener {
            val datePickerDialog = DatePickerDialog(requireContext(),
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    mView.dob_picker_btn.text = "$dayOfMonth/${month + 1}/$year"
                    dob[Calendar.DAY_OF_MONTH] = dayOfMonth
                    dob[Calendar.MONTH] = month
                    dob[Calendar.YEAR] = year
                }, dob[Calendar.YEAR], dob[Calendar.MONTH], dob[Calendar.DAY_OF_MONTH]
            )

            datePickerDialog.show()
        }

    }

    /**
     * create the User object from the data entered
     *
     * @return User
     */
    private fun createUser(): User {
        val goals = arrayListOf<String>()
        if (mView.burn_fat.isChecked) {
            goals.add(mView.burn_fat.text.toString())
        }
        if (mView.relieve_stress.isChecked) {
            goals.add(mView.relieve_stress.text.toString())
        }
        if (mView.build_muscle.isChecked) {
            goals.add(mView.build_muscle.text.toString())
        }
        if (mView.improve_fitness.isChecked) {
            goals.add(mView.improve_fitness.text.toString())
        }
        if (mView.increase_endurance.isChecked) {
            goals.add(mView.increase_endurance.text.toString())
        }


        return User(mView.name_il.editText!!.text.toString(),
            when (mView.gender_rg.checkedRadioButtonId) {
                R.id.M -> "M"
                R.id.F -> "F"
                else -> ""
            },
            mView.dob_picker_btn.text.toString(),
            dob.timeInMillis,
            mView.height_il.editText!!.text.toString().toInt(),
            mView.weight_il.editText!!.text.toString().toInt(),
            mView.findViewById<AppCompatRadioButton>(mView.freq_rg.checkedRadioButtonId).text.toString(),
            mView.findViewById<AppCompatRadioButton>(mView.fitness_rg.checkedRadioButtonId).text.toString(),
            goals
        )
    }

    /**
     * add the user to shared Preference
     *
     * @param user
     */
    private fun addDataToSharedPref(user: User){
        val sharedPreferences = requireActivity().application.getSharedPreferences(
            SHARED_PREF_NAME,
            MODE_PRIVATE
        )
        //store the details in shared pref as well
        val editor = sharedPreferences.edit()
        editor.putString(PREF_NAME, user.name)
        editor.putString(PREF_GENDER, user.gender)
        editor.putLong(PREF_DOB, dob.timeInMillis)
        editor.putInt(PREF_HEIGHT, user.height)
        editor.putInt(PREF_WEIGHT, user.weight)
        editor.putInt(PREF_FREQUENCY, when (mView.freq_rg.checkedRadioButtonId) {
            R.id.hardly -> 0
            R.id.few -> 1
            R.id.moderate -> 2
            R.id.heavy -> 3
            else -> -1
        })

        editor.putInt(PREF_FITNESS_LEVEL, when (mView.fitness_rg.checkedRadioButtonId) {
            R.id.begin -> 0
            R.id.intermediate -> 1
            R.id.pro -> 2
            else -> -1
        })

        editor.putBoolean(PREF_FAT, mView.burn_fat.isChecked)
        editor.putBoolean(PREF_STRESS, mView.relieve_stress.isChecked)
        editor.putBoolean(PREF_MUSCLE, mView.build_muscle.isChecked)
        editor.putBoolean(PREF_FITNESS, mView.improve_fitness.isChecked)
        editor.putBoolean(PREF_ENDURANCE, mView.increase_endurance.isChecked)

        editor.apply()
    }

    /**
     * TODO validate the given input
     *
     * @return
     */
    fun validateInput(): Boolean {
        if (mView.name_il.editText!!.text.isNullOrEmpty()) {
            mView.name_il.error = "please fill your name"
            mView.name_il.editText!!.requestFocus()
            return false
        }
        if (mView.dob_picker_btn.text.trim() == "DD/MM/YYYY") {
            showSnackbar("Enter your date of birth")
            mView.dob_picker_btn.requestFocus()
            return false
        }
        if (mView.height_il.editText!!.text.isNullOrEmpty()) {
            mView.height_il.error = "please fill your height"
            mView.height_il.editText!!.requestFocus()
            return false
        }
        if (mView.weight_il.editText!!.text.isNullOrEmpty()) {
            mView.weight_il.error = "please fill your weight"
            mView.weight_il.editText!!.requestFocus()
            return false
        }
        return true
    }


}

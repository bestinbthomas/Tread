package com.treadhill.app.views.onboarding


import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.treadhill.app.R
import com.treadhill.app.highOrder.RC_SIGN_IN
import com.treadhill.app.highOrder.showSnackbar
import com.treadhill.app.utilities.CountrySpinnerUtils
import kotlinx.android.synthetic.main.fragment_sign_up.view.*


/**
 * A simple [Fragment] subclass.
 */
class SignUpFragment : Fragment() {


    lateinit var mView: View
    lateinit var emailPhoneInputLayout: TextInputLayout
    lateinit var passwordInputLayout: TextInputLayout
    lateinit var confirmPasswordInputLayout: TextInputLayout
    private var isEmail = true
    private var mAuth = FirebaseAuth.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mView = view
    }

    override fun onStart() {
        super.onStart()

        emailPhoneInputLayout = mView.email_phone_il
        passwordInputLayout = mView.password_il
        confirmPasswordInputLayout = mView.confirm_password_il

        mView.countryListSpinner.adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_dropdown_item, CountrySpinnerUtils.countryNames)
        mView.countryListSpinner.setSelection(79)

        mView.sign_up_btn.setOnClickListener {
            if (validateInput()) {
                when (isEmail) {
                    true -> signUpWithEmailPassword()
                    false -> signUpWithPhonePassword()
                }
            }
        }

        mView.log_in_btn.setOnClickListener {
            findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToLogInFragment())
        }

        mView.google_sign_up.setOnClickListener {
            val gso: GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            GoogleSignIn.getClient(requireActivity(), gso)
            val signInIntent = GoogleSignIn.getClient(requireActivity(), gso).signInIntent
            requireActivity().startActivityForResult(signInIntent, RC_SIGN_IN)
        }

        mView.radio_email_phone.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.email -> emailSelected()
                R.id.phone -> phoneSelected()
            }
        }
    }

    private fun emailSelected() {
        isEmail = true
        emailPhoneInputLayout.editText!!.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        passwordInputLayout.visibility = View.VISIBLE
        emailPhoneInputLayout.hint = "Email Address"
        confirmPasswordInputLayout.visibility = View.VISIBLE
        mView.countryListSpinner.visibility = View.GONE
    }

    private fun phoneSelected() {
        isEmail = false
        emailPhoneInputLayout.editText!!.inputType = InputType.TYPE_CLASS_PHONE
        passwordInputLayout.visibility = View.GONE
        confirmPasswordInputLayout.visibility = View.GONE
        emailPhoneInputLayout.hint = "Phone Number"
        mView.countryListSpinner.visibility = View.VISIBLE

    }

    private fun signUpWithEmailPassword() {
        mAuth.createUserWithEmailAndPassword(
                emailPhoneInputLayout.editText!!.text.toString(),
                passwordInputLayout.editText!!.text.toString()
            )
            .addOnCompleteListener(
                requireActivity()
            ) { task ->
                if (task.isSuccessful) { // Sign in success, update UI with the signed-in user's information
                    Log.d("Auth", "createUserWithEmail:success")
                    val user = mAuth.currentUser
                    updateUI(user)
                } else { // If sign in fails, display a message to the user.
                    Log.e("Auth", "createUserWithEmail:failure", task.exception)
                    showSnackbar("Authentication failed.")
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        user?.let {
            findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToCheckUserOnboardingDoneFragment(it.uid))
        }

    }

    private fun signUpWithPhonePassword() {
        val phoneNumber = "+${CountrySpinnerUtils.countryAreaCodes[mView.countryListSpinner.selectedItemPosition]}${mView.email_phone_il.editText!!.text}"
        showSnackbar(phoneNumber)
        findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToVerifyPhoneFragment(phoneNumber))
    }


    private fun validateInput(): Boolean {
        if (emailPhoneInputLayout.editText!!.text.isNullOrEmpty()) {
            emailPhoneInputLayout.error = "This field cannot be blank"
            return false
        }


        if (isEmail) {
            if (passwordInputLayout.editText!!.text.isNullOrEmpty()) {
                passwordInputLayout.error = "This field cannot be blank"
                return false
            }

            if (confirmPasswordInputLayout.editText!!.text.isNullOrEmpty()) {
                confirmPasswordInputLayout.error = "This field cannot be blank"
                return false
            }
            if (passwordInputLayout.editText!!.text.toString() != confirmPasswordInputLayout.editText!!.text.toString()) {
                confirmPasswordInputLayout.error = "Passwords do not match"
                return false
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(emailPhoneInputLayout.editText!!.text).matches()) {
                emailPhoneInputLayout.error = "enter valid email address"
                return false
            }
        } else if (!Patterns.PHONE.matcher(emailPhoneInputLayout.editText!!.text).matches()) {
            emailPhoneInputLayout.error = "enter valid phone number"
            return false
        }

        return true
    }


}

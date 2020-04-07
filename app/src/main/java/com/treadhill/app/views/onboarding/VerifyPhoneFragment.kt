package com.treadhill.app.views.onboarding

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.treadhill.app.R
import com.treadhill.app.highOrder.showSnackbar
import kotlinx.android.synthetic.main.fragment_verify_phone.view.*
import java.util.concurrent.TimeUnit

/**
 * Fragment to check the OTP sent to the user
 *
 */
class VerifyPhoneFragment : Fragment() {

    private var codeSent = false
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var storedVerificationId: String
    private lateinit var code: String
    lateinit var mView: View
    val mAuth = FirebaseAuth.getInstance()

    /**
     *
     */
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mView = view
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_verify_phone, container, false)
    }

    override fun onStart() {
        super.onStart()

        val phoneNumber = navArgs<VerifyPhoneFragmentArgs>().value.phoneNumber

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                /**
                 *  This callback will be invoked in two situations:
                 1 - Instant verification. In some cases the phone number can be instantly
                     verified without needing to send or enter a verification code.
                 2 - Auto-retrieval. On some devices Google Play services can automatically
                     detect the incoming verification SMS and perform verification without
                     user action.
                 */
                Log.d("Auth", "onVerificationCompleted:$credential")
                code = credential.smsCode ?: ""
                mView.otp_edit_text!!.setText(code, TextView.BufferType.EDITABLE)

                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                 /**
                  * This callback is invoked in an invalid request for verification is made,
                 for instance if the the phone number format is not valid.
                  */
                Log.w("Auth", "onVerificationFailed", e)

                if (e is FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    // ...
                    showSnackbar("invalid request")
                } else if (e is FirebaseTooManyRequestsException) {
                    showSnackbar("Too many requests sent from the number")
                }

            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d("Auth", "onCodeSent:$verificationId")

                // Save verification ID and resending token so we can use them later
                storedVerificationId = verificationId
                resendToken = token

                codeSent = true
            }
        }

        mView.subHead.text = requireActivity().resources.getString(R.string.verification_send_to, phoneNumber)

        logInWithPhone(phoneNumber)

        mView.submit_btn.setOnClickListener {
            if (codeSent) {
                val credential = PhoneAuthProvider.getCredential(storedVerificationId, mView.otp_edit_text.text.toString())
                signInWithPhoneAuthCredential(credential)
            }
        }
        mView.resend_btn.setOnClickListener {
            if (codeSent) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    phoneNumber, // Phone number to verify
                    60, // Timeout duration
                    TimeUnit.SECONDS, // Unit of timeout
                    requireActivity(), // Activity (for callback binding)
                    callbacks,
                    resendToken)
            }
        }
    }

    /**
     * request OTP from firebase
     *
     * @param phoneNumber
     */
    fun logInWithPhone(phoneNumber: String) {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            phoneNumber, // Phone number to verify
            60, // Timeout duration
            TimeUnit.SECONDS, // Unit of timeout
            requireActivity(), // Activity (for callback binding)
            callbacks) // OnVerificationStateChangedCallbacks
    }

    /**
     * sigin in with the credentials
     *
     * @param credential
     */
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Auth", "signInWithCredential:success")

                    val user = task.result?.user
                    user?.let {
                        findNavController().navigate(VerifyPhoneFragmentDirections.actionVerifyPhoneFragmentToCheckUserOnboardingDoneFragment(it.uid))
                    }
                    // ...
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w("Auth", "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        Log.e("AUTH", "phone verification failed", task.exception)
                        showSnackbar("Invalid verification code")
                    }
                }
            }
    }

}

package com.eru.les3_m4.ui.auth

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.eru.les3_m4.databinding.FragmentLoginBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var verificationId: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(LayoutInflater.from(requireContext()), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnContinue.setOnClickListener {
            requestSMS()
            binding.editPhone.visibility = View.INVISIBLE
            binding.btnContinue.visibility = View.INVISIBLE
            binding.editCode.visibility = View.VISIBLE
            binding.btnConfirm.visibility = View.VISIBLE
            object : CountDownTimer(60000, 1000){
                override fun onTick(p0: Long) {
                    binding.countDown.visibility = View.VISIBLE
                    binding.countDown.text = "${p0/1000}"
                }

                override fun onFinish() {
                    binding.editPhone.visibility = View.VISIBLE
                    binding.btnContinue.visibility = View.VISIBLE
                    binding.editCode.visibility = View.INVISIBLE
                    binding.btnConfirm.visibility = View.INVISIBLE
                    binding.countDown.visibility = View.INVISIBLE
                }
            }
        }

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                Log.e("Login", "onVerificationCompleted:")
//                signIn(credential)
            }

            override fun onVerificationFailed(exception: FirebaseException) {
                Log.e("Login", "onVerificationFailed: ${exception.message}")
            }

            override fun onCodeSent(id: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(id, p1)
                verificationId = id
            }
        }

        binding.btnConfirm.setOnClickListener {
            val credential = PhoneAuthProvider.getCredential(verificationId, binding.editCode.text.toString())
            signIn(credential)
        }
    }

    private fun signIn(credential: PhoneAuthCredential) {
        Firebase.auth.signInWithCredential(credential).addOnCompleteListener {
            if(it.isSuccessful) {
                Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            } else
                Toast.makeText(requireContext(), "Error: ${it.exception?.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun requestSMS() {
        val phoneNumber = binding.editPhone.text.toString()
        val options = PhoneAuthOptions.newBuilder(Firebase.auth)
            .setPhoneNumber("+996$phoneNumber")       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(requireActivity())                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
}
package com.example.geekouttry

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.example.geekouttry.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase


class LoginFragment : Fragment() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: FragmentLoginBinding
    private lateinit var myViewModel: AccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Use the provided ViewBinding class to inflate the layout.
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        // Return the root view.
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myViewModel = ViewModelProvider(this)[AccountViewModel::class.java]
        firebaseAuth = requireNotNull(FirebaseAuth.getInstance())

        binding.loginButton.setOnClickListener { loginUserAccount() }
        binding.regiButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment) }

    }

    private fun loginUserAccount() {
        val email: String = binding.emailAddress3.text.toString()
        val pass: String = binding.password.text.toString()

        if (TextUtils.isEmpty((email))){
            Toast.makeText(
                requireContext(),
                getString(R.string.login_toast),
                Toast.LENGTH_LONG
            ).show()
            return
        }

        if (TextUtils.isEmpty(pass)){
            Toast.makeText(
                requireContext(),
                getString(R.string.password_toast),
                Toast.LENGTH_LONG
            ).show()
            return
        }

        binding.progressBar.visibility = View.VISIBLE

        binding.loginButton.isEnabled = false
        binding.loginButton.isClickable = false

        binding.regiButton.isEnabled = false
        binding.regiButton.isClickable = false

        firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener{ task ->
            binding.progressBar.visibility = View.GONE
            if (task.isSuccessful){
                Toast.makeText(
                    requireContext(),
                    getString(R.string.login_success_toast),
                    Toast.LENGTH_LONG
                ).show()

                //myViewModel.updateUser(email)
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.login_fail_toast),
                    Toast.LENGTH_LONG
                ).show()

                binding.loginButton.isEnabled = true
                binding.loginButton.isClickable = true

                binding.regiButton.isEnabled = true
                binding.regiButton.isClickable = true


            }
        }
    }


}
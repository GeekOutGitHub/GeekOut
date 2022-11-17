package com.example.geekouttry

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.geekouttry.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class RegisterFragment : Fragment() {

    private var validator = Validators()
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseAccount: DatabaseReference
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)

        // Return the root view.
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = requireNotNull(FirebaseAuth.getInstance())
        databaseAccount = FirebaseDatabase.getInstance().getReference("accounts")

        binding.registerButton.setOnClickListener { registerNewUser() }
        binding.logButton.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment) }
    }


    private fun registerNewUser() {
        val email: String = binding.emailAddress3.text.toString()
        val pass: String = binding.password.text.toString()
        val nickname: String = binding.editTextTextPersonName.text.toString()

        if (!validator.validEmail(email)){
            Toast.makeText(
                requireContext(),
                getString(R.string.invalid_email),
                Toast.LENGTH_LONG
            ).show()
            return
        }

        if (TextUtils.isEmpty(nickname)){
            Toast.makeText(
                requireContext(),
                getString(R.string.nickname),
                Toast.LENGTH_LONG
            ).show()
            return
        }

        if (!validator.validPassword(pass)){
            Toast.makeText(
                requireContext(),
                getString(R.string.invalid_password),
                Toast.LENGTH_LONG
            ).show()
            return
        }

        binding.progressBar2.visibility = View.VISIBLE

        binding.logButton.isEnabled = false
        binding.logButton.isClickable = false

        binding.registerButton.isEnabled = false
        binding.registerButton.isClickable = false

        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener{task ->
            binding.progressBar2.visibility =View.GONE
            if (task.isSuccessful) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.register_success_string),
                    Toast.LENGTH_LONG
                ).show()

                //TODO: make account object and save it to the base
                val id = databaseAccount.push().key
                val account = Account(email, nickname, id!!, 0,"OFF")
                databaseAccount.addValueEventListener(object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        databaseAccount.child(id).setValue(account)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        return
                    }

                })


                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)

            }else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.register_failed_string),
                    Toast.LENGTH_LONG
                ).show()

                binding.logButton.isEnabled = true
                binding.logButton.isClickable = true

                binding.registerButton.isEnabled = true
                binding.registerButton.isClickable = true
            }
        }

    }


}
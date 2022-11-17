package com.example.geekouttry

import android.content.Context
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AccountRepository {

    private val firebaseAuth: FirebaseAuth = requireNotNull(FirebaseAuth.getInstance())
    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("accounts")

    @Volatile private var INSTANCE : AccountRepository ?= null

    fun getInstance(): AccountRepository {
        return INSTANCE ?: synchronized(this){

            val instance = AccountRepository()
            INSTANCE = instance
            instance

        }
    }

    internal fun loginUserAccount(email: String, pass: String, context: Context) {

        if (TextUtils.isEmpty((email))){
            Toast.makeText(
                context,
                "Please Enter Email!!",
                Toast.LENGTH_LONG
            ).show()
            return
        }

        if (TextUtils.isEmpty(pass)){
            Toast.makeText(
                context,
                "Please Enter your Password!!",
                Toast.LENGTH_LONG
            ).show()
            return
        }


        firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener{ task ->
            if (task.isSuccessful){
                Toast.makeText(
                    context,
                    "Login Successful!",
                    Toast.LENGTH_LONG
                ).show()

            }else {
                Toast.makeText(
                    context,
                    "Invalid Email or Password!!",
                    Toast.LENGTH_LONG
                ).show()


            }
        }
    }

}
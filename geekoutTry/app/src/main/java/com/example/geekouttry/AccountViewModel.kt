package com.example.geekouttry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AccountViewModel: ViewModel() {

    private var databaseAccount: DatabaseReference = FirebaseDatabase.getInstance().getReference("accounts")
    private var databaseQuestions: DatabaseReference = FirebaseDatabase.getInstance().getReference("Questions")



    private var _repository: AccountRepository = AccountRepository().getInstance()
    val repository : AccountRepository = _repository

    private var _myaccount = MutableLiveData<Account>()
    val myaccount: LiveData<Account> = _myaccount






}
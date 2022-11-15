package com.notableFactory.marvelapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel : ViewModel() {

    // TODO Move Firebase instance to another Kotlin file "SessionManager"
    private var auth: FirebaseAuth = Firebase.auth;
    private val _wasLogInSuccessful: MutableLiveData<Boolean> = MutableLiveData(false);
    val wasLogInSuccessful: LiveData<Boolean> get() = _wasLogInSuccessful;

    private val _wasRegisterSuccessful: MutableLiveData<Boolean> = MutableLiveData(false);
    val wasRegisterSuccessful: LiveData<Boolean> get() = _wasRegisterSuccessful;

    fun logUserIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
        // TODO Perform changes on LiveData based on Task Response
        _wasLogInSuccessful.value = true
    }

    fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
        // TODO Perform changes on LiveData based on Task Response
        _wasRegisterSuccessful.value = true
    }
}
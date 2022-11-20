package com.notableFactory.marvelapp.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.notableFactory.marvelapp.R
import com.notableFactory.marvelapp.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel = LoginViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}
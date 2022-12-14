package com.notableFactory.marvelapp.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.notableFactory.marvelapp.R
import com.notableFactory.marvelapp.ui.home.HomeActivity
import com.notableFactory.marvelapp.utils.Ui
import com.notableFactory.marvelapp.utils.replaceFragment
import com.notableFactory.marvelapp.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity(), LoginFragment.OnLoginFragmentInteractionListener, RegisterFragment.OnRegisterFragmentInteractionListener {

    private val viewModel: LoginViewModel = LoginViewModel()
    private val fragmentManager = supportFragmentManager
    private val fragmentsContainerId = R.id.loginFragmentContainer
    private var wasViewModelCalledOnce: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val image: ImageView = findViewById(R.id.loginTop)
        image.setImageResource(R.drawable.backgrounlogin)
        showLoginForm()
        setObservers()
    }

    private fun showLoginForm() {
        val loginFragment = LoginFragment()
        replaceFragment(loginFragment, fragmentManager, fragmentsContainerId)
    }

    private fun showRegisterForm() {
        val registerFragment = RegisterFragment()
        replaceFragment(registerFragment, fragmentManager, fragmentsContainerId)
    }



    override fun logInUser(email: String, password: String) {
        viewModel.logUserIn(email, password)
        wasViewModelCalledOnce = true
        this.currentFocus?.let { Ui.closeKeyboard(it, this) }
    }

    override fun switchToRegisterForm() {
        showRegisterForm()
    }

    override fun createUser(email: String, password: String) {
        viewModel.registerUser(email, password)
        wasViewModelCalledOnce = true
        this.currentFocus?.let { Ui.closeKeyboard(it, this) }
    }

    override fun switchToLoginForm() {
        showLoginForm()
    }

    private fun setObservers() {

        viewModel.wasLogInSuccessful.observe(this) {
                loggedIn ->
            if (wasViewModelCalledOnce) {
                var logInResultMessage: String = "Wrong Credentials"
                if (loggedIn) {
                    logInResultMessage = "Nice to see you again!"
                    finish()
                    val myIntent = Intent(this, HomeActivity::class.java)
                    this.startActivity(myIntent)
                }
                val toast = Toast.makeText(this, logInResultMessage, Toast.LENGTH_SHORT)
                toast.show()
            }

        }

        viewModel.wasRegisterSuccessful.observe(this ) {
                signedUp ->
            if (wasViewModelCalledOnce) {
                var signedUpResultMessage: String = "An error occurred, check your data"
                if (signedUp) {
                    signedUpResultMessage = "Welcome!"
                    finish()
                    val myIntent = Intent(this, HomeActivity::class.java)
                    this.startActivity(myIntent)
                }
                val toast = Toast.makeText(this, signedUpResultMessage, Toast.LENGTH_SHORT)
                toast.show()
            }
        }

    }
}
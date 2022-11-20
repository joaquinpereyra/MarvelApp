package com.notableFactory.marvelapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.notableFactory.marvelapp.R
import com.notableFactory.marvelapp.ui.login.LoginFragment
import com.notableFactory.marvelapp.ui.login.RegisterFragment
import com.notableFactory.marvelapp.utils.Ui.closeKeyboard
import com.notableFactory.marvelapp.viewmodel.LoginViewModel


class MainActivity : AppCompatActivity(), LoginFragment.OnLoginFragmentInteractionListener, RegisterFragment.OnRegisterFragmentInteractionListener {

    private val viewModel: LoginViewModel = LoginViewModel()
    private val fragmentManager = supportFragmentManager
    private val fragmentsContainerId = R.id.loginFragmentContainer
    private var wasViewModelCalledOnce: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val image : ImageView = findViewById(R.id.loginTop)
        image.setImageResource(R.drawable.backgrounlogin)
        showLoginForm()
        setObservers()
    }

    private fun showLoginForm() {
       val loginFragment = LoginFragment()
        replaceFragment(loginFragment)
    }

    private fun showRegisterForm() {
        val registerFragment = RegisterFragment()
        replaceFragment(registerFragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(fragmentsContainerId, fragment)
        fragmentTransaction.commit()
    }

    override fun logInUser(email: String, password: String) {
        viewModel.logUserIn(email, password)
        wasViewModelCalledOnce = true
        this.currentFocus?.let { closeKeyboard(it, this) }
    }

    override fun switchToRegisterForm() {
       showRegisterForm()
    }

    override fun createUser(email: String, password: String) {
       viewModel.registerUser(email, password)
        wasViewModelCalledOnce = true
        this.currentFocus?.let { closeKeyboard(it, this) }
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
                    showLoginForm()
                }
                val toast = Toast.makeText(this, signedUpResultMessage, Toast.LENGTH_SHORT)
                toast.show()
            }
        }

    }
}

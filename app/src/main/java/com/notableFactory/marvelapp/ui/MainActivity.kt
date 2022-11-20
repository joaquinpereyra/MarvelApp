package com.notableFactory.marvelapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment

import com.notableFactory.marvelapp.R
import com.notableFactory.marvelapp.utils.Ui.closeKeyboard
import com.notableFactory.marvelapp.viewmodel.LoginViewModel

class MainActivity : AppCompatActivity(), LoginFragment.OnLoginFragmentInteractionListener {

    private val viewModel: LoginViewModel = LoginViewModel()
    private val fragmentManager = supportFragmentManager
    private val fragmentsContainerId = R.id.loginFragmentContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

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
        this.currentFocus?.let { closeKeyboard(it, this) }
    }

    override fun switchToRegisterForm() {
       showRegisterForm()
    }

    private fun setObservers() {

        viewModel.wasLogInSuccessful.observe(this) {
            loggedIn ->
            var logInResultMessage: String = "Wrong Credentials"
            Log.v("Batman", loggedIn.toString())
            if (loggedIn) {
                logInResultMessage = "Nice to see you again!"
            }
            val toast = Toast.makeText(this, logInResultMessage, Toast.LENGTH_SHORT)
            toast.show()
        }
    }
}

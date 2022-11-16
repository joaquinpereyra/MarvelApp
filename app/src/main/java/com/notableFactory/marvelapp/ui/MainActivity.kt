package com.notableFactory.marvelapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.notableFactory.marvelapp.R
import com.notableFactory.marvelapp.ui.adapters.HeroesListAdapter
import com.notableFactory.marvelapp.viewmodel.HeroesViewModel
import com.notableFactory.marvelapp.viewmodel.LoginViewModel

class MainActivity : AppCompatActivity(), LoginFragment.OnLoginFragmentInteractionListener {

    private val viewModel: LoginViewModel = LoginViewModel()
    private val fragmentManager = supportFragmentManager
    private val fragmentsContainerId = R.id.loginFragmentContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        showLoginForm()
    }

    private fun showLoginForm() {
       val loginFragment = LoginFragment()
        replaceFragment(loginFragment)
    }

    private fun showRegisterForm() {
        // TODO Change to RegisterFragment
        val loginFragment = LoginFragment()
        replaceFragment(loginFragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(fragmentsContainerId, fragment)
        fragmentTransaction.commit()
    }

    override fun logInUser() {
        TODO("Not yet implemented")
    }
}
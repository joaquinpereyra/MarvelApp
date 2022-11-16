package com.notableFactory.marvelapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.notableFactory.marvelapp.R
import com.notableFactory.marvelapp.ui.adapters.HeroesListAdapter
import com.notableFactory.marvelapp.viewmodel.HeroesViewModel
import com.notableFactory.marvelapp.viewmodel.LoginViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel = LoginViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}
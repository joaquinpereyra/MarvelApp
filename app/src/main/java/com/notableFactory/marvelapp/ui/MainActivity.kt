package com.notableFactory.marvelapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.notableFactory.marvelapp.R
import com.notableFactory.marvelapp.ui.adapters.HeroesListAdapter
import com.notableFactory.marvelapp.viewmodel.HeroesViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: HeroesViewModel = HeroesViewModel()
    private val adapter = HeroesListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val heroesRecyclerView:RecyclerView = findViewById(R.id.heroesRecyclerView)
        heroesRecyclerView.layoutManager = LinearLayoutManager(this)
        heroesRecyclerView.adapter = adapter

        setObservers()
    }

    private fun setObservers() {
        viewModel.heroesList.observe(this) {
            superHeroList ->
            adapter.heroes = superHeroList
            adapter.notifyDataSetChanged()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchCharacters()
    }
}
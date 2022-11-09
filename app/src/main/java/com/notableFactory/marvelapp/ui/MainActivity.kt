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

class MainActivity : AppCompatActivity() {

    private val viewModel: HeroesViewModel = HeroesViewModel()
    private val adapter = HeroesListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val firstFragment=FavoritesFragment()
        val secondFragment=BrowseFragment()
        val thirdFragment=UserFragment()

        //setCurrentFragment(firstFragment)
/*
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home->setCurrentFragment(firstFragment)
                R.id.person->setCurrentFragment(secondFragment)
                R.id.settings->setCurrentFragment(thirdFragment)

            }
            true
        }

    }

    private fun setCurrentFragment(fragment:Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }*/
       /* val heroesRecyclerView:RecyclerView = findViewById(R.id.heroesRecyclerView)
        heroesRecyclerView.layoutManager = LinearLayoutManager(this)
        heroesRecyclerView.adapter = adapter
*/
        val starButton = findViewById<ImageButton>(R.id.imageButton2)

        starButton.setOnClickListener {
            starButton.setImageResource(R.drawable.star_relleno)
        }
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
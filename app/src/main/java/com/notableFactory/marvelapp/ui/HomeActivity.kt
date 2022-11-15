package com.notableFactory.marvelapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.notableFactory.marvelapp.R
import com.notableFactory.marvelapp.ui.adapters.HeroesListAdapter
import com.notableFactory.marvelapp.viewmodel.HeroesViewModel

class HomeActivity : AppCompatActivity() {

    private val viewModel: HeroesViewModel = HeroesViewModel()
    private val adapter = HeroesListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        val favoritesFragment = FavoritesFragment()
        val browseFragment = BrowseFragment()
        val userFragment = UserFragment()

    setCurrentFragment(browseFragment)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.favoritesItem->setCurrentFragment(favoritesFragment)
                R.id.browseItem->setCurrentFragment(browseFragment)
                R.id.userItem->setCurrentFragment(userFragment)

            }
            true
        }

    }

    private fun setCurrentFragment(fragment:Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }
}

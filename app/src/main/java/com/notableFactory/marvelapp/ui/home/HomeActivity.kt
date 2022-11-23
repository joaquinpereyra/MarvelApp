package com.notableFactory.marvelapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI
import com.notableFactory.marvelapp.R
import com.notableFactory.marvelapp.databinding.HomeActivityBinding
import com.notableFactory.marvelapp.model.SuperHero
import com.notableFactory.marvelapp.ui.Heroe.SuperHeroeActivity
import com.notableFactory.marvelapp.utils.addFragment
import com.notableFactory.marvelapp.utils.replaceFragment
import com.notableFactory.marvelapp.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeActivity : AppCompatActivity(), BrowseFragment.OnBrowseFragmentInteractionListener {


    private lateinit var navController: NavController
    private lateinit var homeBinding: HomeActivityBinding
    private val fragmentManager = supportFragmentManager
    private val fragmentContainer = R.id.fragmentContainer
    private val homeViewModel by viewModel<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = HomeActivityBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)

        fragmentManager.addFragment(fragmentContainer, FavoritesFragment(), true, null)


        homeBinding.bottomNavigationView.setOnItemSelectedListener {

            when (it.itemId) {

                R.id.favoritesFragmentItem -> fragmentManager.replaceFragment(
                    fragmentContainer,
                    FavoritesFragment(), false, null
                )
                R.id.browseFragmentItem -> {
                    fragmentManager.replaceFragment(
                        fragmentContainer,
                        BrowseFragment(), false, null
                    )


                }
                R.id.userFragmentItem -> fragmentManager.replaceFragment(
                    fragmentContainer,
                    UserFragment(), false, null
                )
                else -> {
                }
            }
            true
        }



        setObservers()

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }

    private fun setObservers() {
        homeViewModel.heroesList.observe(this) { superHeroList ->

        }

    }

    override fun onSuperHeroClick(heroe: SuperHero, imageView:ImageView) {
        val intent = Intent(this, SuperHeroeActivity::class.java)
        intent.putExtra("heroe",heroe)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this@HomeActivity,
            imageView,
            "transitionHeroImage"
        )
        //finish()
        this.startActivity(intent,options.toBundle())
    }
}
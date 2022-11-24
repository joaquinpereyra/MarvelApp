package com.notableFactory.marvelapp.ui.Heroe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.notableFactory.marvelapp.R
import com.notableFactory.marvelapp.databinding.ActivitySuperHeroeBinding
import com.notableFactory.marvelapp.model.SuperHero
import com.notableFactory.marvelapp.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SuperHeroeActivity : AppCompatActivity() {

    private lateinit var hero:SuperHero

    private lateinit var heroeImageView: ImageView
    private lateinit var heroeNameTextView: TextView
    private lateinit var heroeDescription: TextView
    private lateinit var heroeBinding: ActivitySuperHeroeBinding

    private val homeViewModel by viewModel<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_super_heroe)
        heroeBinding = ActivitySuperHeroeBinding.inflate(layoutInflater)
        setContentView(heroeBinding.root)
        loadData()

        heroeNameTextView.setText(hero.name)
        heroeDescription.setText(if (!hero.description.isNullOrEmpty()) hero.description else "${hero.name} not have description.")
        heroeImageView.load("${hero.thumbnailUrl}/portrait_xlarge.${hero.thumbnailExt}")

        setObservers()
    }


    private fun loadData(){
        hero = intent.getParcelableExtra<SuperHero>("heroe") as SuperHero

        homeViewModel.fetchCharacterComics(hero.id)

        heroeImageView = heroeBinding.heroeImageView
        heroeNameTextView = heroeBinding.heroeName
        heroeDescription = heroeBinding.heroeDescription
    }

    private fun setObservers() {
        homeViewModel.comicsList.observe(this) { comicsList ->
            //TODO Load comics data into recycler view
        }
    }
}
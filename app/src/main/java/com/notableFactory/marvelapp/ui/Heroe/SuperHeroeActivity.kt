package com.notableFactory.marvelapp.ui.Heroe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.airbnb.lottie.LottieAnimationView
import com.notableFactory.marvelapp.R
import com.notableFactory.marvelapp.databinding.ActivitySuperHeroeBinding
import com.notableFactory.marvelapp.model.MarvelComic
import com.notableFactory.marvelapp.model.SuperHero
import com.notableFactory.marvelapp.ui.comic.ComicActivity
import com.notableFactory.marvelapp.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.notableFactory.marvelapp.model.User
import com.notableFactory.marvelapp.repositories.DatabaseHandler
import com.notableFactory.marvelapp.ui.adapters.ComicListAdapter
import com.notableFactory.marvelapp.ui.adapters.HeroesListAdapter
import com.notableFactory.marvelapp.viewmodel.SuperHeroViewModel

class SuperHeroeActivity : AppCompatActivity() {

    private lateinit var hero: SuperHero
    private lateinit var user: User

    private lateinit var heroeImageView: ImageView
    private lateinit var heroeNameTextView: TextView
    private lateinit var heroeDescription: TextView
    private lateinit var heroeBinding: ActivitySuperHeroeBinding
    private var comicsAdapter : ComicListAdapter = ComicListAdapter()

    private val comicViewModel by viewModel<SuperHeroViewModel>()
    private lateinit var lottieFavHero: LottieAnimationView
    var pressed = false
    private lateinit var userEmail: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_super_heroe)
        heroeBinding = ActivitySuperHeroeBinding.inflate(layoutInflater)
        setContentView(heroeBinding.root)


        loadData()


        val heroesRecyclerView: RecyclerView = heroeBinding.heroComicsRecycledView
        heroesRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        heroesRecyclerView.adapter = comicsAdapter


        comicsAdapter.setOnItemClickListener(object : ComicListAdapter.onItemClickListener {
            override fun onItemClick(comic: MarvelComic) {
                openComicDetailsScreen(comic)
            }
        })

        userEmail = intent.getStringExtra("userEmail").toString()


        heroeNameTextView.setText(hero.name)
        heroeDescription.setText(if (!hero.description.isNullOrEmpty()) hero.description else "${hero.name} not have description.")
        heroeImageView.load("${hero.thumbnailUrl}/portrait_xlarge.${hero.thumbnailExt}")


        lottieFavHero.setOnClickListener {
            if (pressed) {
                lottieFavHero.speed = -3.0f
                lottieFavHero.playAnimation()
                pressed = false
                removeFavoriteHero()
            } else {
                lottieFavHero.speed = 3.0f
                lottieFavHero.playAnimation()
                pressed = true
                saveFavoriteHero()
            }
        }

        setObservers()
    }

    private fun saveFavoriteHero() {
        user.favoritesHeroes.add(hero)
        DatabaseHandler.saveUser(user)
    }

    private fun removeFavoriteHero() {
        user.favoritesHeroes.remove(hero)
        DatabaseHandler.saveUser(user)
    }


    private fun loadData() {


        hero = intent.getParcelableExtra<SuperHero>("hero") as SuperHero
        user = intent.getParcelableExtra<User>("user") as User
        comicViewModel.fetchCharacterComics(hero.id)
        heroeImageView = heroeBinding.heroeImageView
        heroeNameTextView = heroeBinding.heroeName
        heroeDescription = heroeBinding.heroeDescription
        lottieFavHero = heroeBinding.favHero

        checkHeroIsFavorite()
    }

    fun setObservers() {
        comicViewModel.comicsList.observe(this) { comicsList ->
            comicsAdapter.setData(comicsList)
            //openComicDetailsScreen(comicsList[0])

        }
    }

    fun openComicDetailsScreen(comic: MarvelComic) {
        val intent = Intent(this, ComicActivity::class.java)
        intent.putExtra("comic", comic)

        this.startActivity(intent)
        lottieFavHero = heroeBinding.favHero


    }

    private fun checkHeroIsFavorite() {
        if (user.favoritesHeroes.contains(hero)) {
            pressed = true
            lottieFavHero.speed = 10f
            lottieFavHero.playAnimation()
        }
    }

}

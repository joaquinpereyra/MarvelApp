package com.notableFactory.marvelapp.ui.Heroe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.notableFactory.marvelapp.R
import com.notableFactory.marvelapp.databinding.ActivitySuperHeroeBinding
import com.notableFactory.marvelapp.databinding.HomeActivityBinding
import com.notableFactory.marvelapp.model.SuperHero

class SuperHeroeActivity : AppCompatActivity() {

    private lateinit var heroe:SuperHero

    private lateinit var heroeImageView: ImageView
    private lateinit var heroeNameTextView: TextView
    private lateinit var heroeDescription: TextView
    private lateinit var heroeBinding: ActivitySuperHeroeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_super_heroe)
        heroeBinding = ActivitySuperHeroeBinding.inflate(layoutInflater)
        setContentView(heroeBinding.root)
        loadData()

        heroeNameTextView.setText(heroe.name)
        heroeDescription.setText(if (!heroe.description.isNullOrEmpty()) heroe.description else "${heroe.name} not have description.")
        heroeImageView.load("${heroe.thumbnailUrl}/portrait_xlarge.${heroe.thumbnailExt}")

    }


    fun loadData(){
        heroe = intent.getSerializableExtra("heroe") as SuperHero

        heroeImageView = heroeBinding.heroeImageView
        heroeNameTextView = heroeBinding.heroeName
        heroeDescription = heroeBinding.heroeDescription



    }
}
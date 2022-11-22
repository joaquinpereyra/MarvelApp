package com.notableFactory.marvelapp.ui.Heroe

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import coil.load
import com.notableFactory.marvelapp.R
import com.notableFactory.marvelapp.model.SuperHero
import kotlinx.coroutines.delay

class SuperHeroeSplashScreen : AppCompatActivity() {

    private lateinit var heroe:SuperHero

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen_layout)

        heroe = intent.getSerializableExtra("heroe") as SuperHero
        val splashScreenImageView:ImageView = findViewById(R.id.splashHeroeScreenImageView)
        splashScreenImageView.load("${heroe.thumbnailUrl}/portrait_xlarge.${heroe.thumbnailExt}")

        Handler().postDelayed({

            val intent = Intent(this, SuperHeroeActivity::class.java)
            intent.putExtra("heroe",heroe)

            finish()

            startActivity(intent)
        },750)


    }
}
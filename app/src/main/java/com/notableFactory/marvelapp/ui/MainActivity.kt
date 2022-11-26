package com.notableFactory.marvelapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.notableFactory.marvelapp.repositories.DatabaseHandler
import com.notableFactory.marvelapp.ui.login.LoginActivity


class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DatabaseHandler.setDatabase(getSharedPreferences("DATABASE", MODE_PRIVATE))
        val myIntent: Intent = Intent(this, LoginActivity::class.java)
        startActivity(myIntent)
        finish()
    }
}

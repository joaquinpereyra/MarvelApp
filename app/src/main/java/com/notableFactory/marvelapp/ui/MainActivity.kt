package com.notableFactory.marvelapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.notableFactory.marvelapp.ui.login.LoginActivity


class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val myIntent: Intent = Intent(this, LoginActivity::class.java)
        startActivity(myIntent)
        finish()
    }
}

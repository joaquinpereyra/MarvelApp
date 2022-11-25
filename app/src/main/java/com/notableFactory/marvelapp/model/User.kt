package com.notableFactory.marvelapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(val email:String, val favoritesHeroes : MutableList<SuperHero>) : Parcelable

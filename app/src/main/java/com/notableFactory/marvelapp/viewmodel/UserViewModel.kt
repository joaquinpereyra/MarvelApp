package com.notableFactory.marvelapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.notableFactory.marvelapp.model.User
import com.notableFactory.marvelapp.repositories.DatabaseHandler

class UserViewModel : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    fun saveFavoriteHero(hero: Hero) {
        user.favoritesHeroes.add(hero)
        DatabaseHandler.saveUser(user)
    }

    fun removeFavoriteHero() {
        user.favoritesHeroes.remove(hero)
        DatabaseHandler.saveUser(user)
    }

}
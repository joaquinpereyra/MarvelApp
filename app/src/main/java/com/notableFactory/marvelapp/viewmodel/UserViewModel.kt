package com.notableFactory.marvelapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.notableFactory.marvelapp.model.SuperHero
import com.notableFactory.marvelapp.model.User
import com.notableFactory.marvelapp.repositories.DatabaseHandler

class UserViewModel : ViewModel() {

    fun createUser(emailUser: String){
       DatabaseHandler.saveUser(User(emailUser, emptyList<SuperHero>().toMutableList()))
    }

    fun getUser(emailUser: String) : User? {
        return DatabaseHandler.getUser(emailUser)
    }

    fun saveFavoriteHero(user: User, hero: SuperHero) {
        user.favoritesHeroes.add(hero)
        DatabaseHandler.saveUser(user)
    }

    fun removeFavoriteHero(user: User, hero: SuperHero) {
        user.favoritesHeroes.remove(hero)
        DatabaseHandler.saveUser(user)
    }



}
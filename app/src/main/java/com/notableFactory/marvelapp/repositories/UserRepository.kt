package com.notableFactory.marvelapp.repositories

import com.notableFactory.marvelapp.model.SuperHero
import com.notableFactory.marvelapp.model.User

object UserRepository {

    private lateinit var user: User

    fun retrieveUserFromSharedPreferences(email: String) {
       DatabaseHandler.getUser(email).let {
           if (it != null) {
               user = it
           }
        }
    }

    fun saveFavoriteHero(hero: SuperHero) {
        user.let {
            user.favoritesHeroes.add(hero)
            DatabaseHandler.saveUser(user)
        }
    }

    fun removeFavoriteHero(hero: SuperHero) {
        user.let {
            user.favoritesHeroes.remove(hero)
            DatabaseHandler.saveUser(user)
        }
    }

    fun checkHeroIsFavorite(hero: SuperHero) : Boolean {
        var result = false
        user.let {
            result = user.favoritesHeroes.contains(hero)
        }
        return result
    }

}
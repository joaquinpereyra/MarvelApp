package com.notableFactory.marvelapp.repositories

import com.google.gson.Gson
import com.notableFactory.marvelapp.model.SuperHero
import com.notableFactory.marvelapp.model.User

class GsonHandler {
    companion object
    {
        private val gson = Gson()


        fun createGsonUser(user: User): String
        {
            return gson.toJson(user)
        }

        fun getUserFromGson(userGson:String):User
        {
            return gson.fromJson(userGson, User::class.java) as User
        }
    }
}
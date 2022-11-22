package com.notableFactory.marvelapp.repositories

import com.google.gson.Gson
import com.notableFactory.marvelapp.model.SuperHero

class GsonHandler {
    companion object
    {
        private val gson = Gson()


        fun createGsonSuperHeroe(heroe:SuperHero): String
        {
            return gson.toJson(heroe)
        }

        fun getSuperHeroeFromGson(superHeroGson:String):SuperHero?
        {
            return gson.fromJson(superHeroGson, SuperHero::class.java) as SuperHero
        }
    }
}
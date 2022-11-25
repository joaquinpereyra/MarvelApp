package com.notableFactory.marvelapp.repositories
import android.content.SharedPreferences
import com.notableFactory.marvelapp.model.SuperHero
import com.notableFactory.marvelapp.model.User

class DatabaseHandler {


    companion object {
        private var sPrefs: SharedPreferences? = null

        /**
         * Función que sirve para setear las preferencias compartidas
         */
        fun setDatabase(database:SharedPreferences)
        {
            sPrefs = database
        }

        /**
         * Función que guarda un usuario en la base de datos
         */
        fun saveUser(userToCreate: User): Boolean? {
            val userJson = GsonHandler.createGsonUser(userToCreate)
            return sPrefs?.edit()?.putString(userToCreate.email, userJson)?.commit()
        }

        /**
         * Función que chequea si un usuario existe en base
         */
        fun userExist(userEmail:String) : Boolean
        {
            val userGson = sPrefs?.getString(userEmail,null)
            if(userGson == null || userGson == "")
            {
                return false
            }
            return true
        }

        /**
         * Función que dado el nombre de un usuario devuelve el objeto user guardado en base
         */
        fun getUser(userEmail: String?): User? {
            val userGson = sPrefs?.getString(userEmail, null)
            return GsonHandler.getUserFromGson(userGson.toString())
        }

        /*
        /**
         * Función que chequea si un heroe existe en base
         */
        fun superHeroeExist(superHeroeID:String) : Boolean
        {
            val superHeroeGson = sPrefs?.getString(superHeroeID,null)
            if(superHeroeGson == null || superHeroeGson == "")
            {
                return false
            }
            return true
        }


        /**
         * Función que guarda un super heroe en la base de datos
         */
        fun saveSuperHeroe(superHeroeToSave: SuperHero): Boolean? {
            val superHeroeJson = GsonHandler.createGsonSuperHeroe(superHeroeToSave)
            return sPrefs?.edit()?.putString(superHeroeToSave.id, superHeroeJson)?.commit()
        }

        /**
         * Función que dado el id de un heroe devuelve el objeto superHero guardado en base
         */
        fun getSuperHeroe(superHeroeID: String?): SuperHero? {
            val superHeroeGson = sPrefs?.getString(superHeroeID, null)
            return GsonHandler.getSuperHeroeFromGson(superHeroeGson.toString())
        }

        fun deleteSuperHeroe(superheroeID: String?):Boolean?{
            return sPrefs?.edit()?.remove(superheroeID)?.commit()
        }*/

    }
}

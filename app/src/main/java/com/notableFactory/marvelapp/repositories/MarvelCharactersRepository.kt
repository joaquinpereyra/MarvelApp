package com.notableFactory.marvelapp.repositories

import com.notableFactory.marvelapp.BuildConfig
import com.notableFactory.marvelapp.model.SuperHero
import com.notableFactory.marvelapp.utils.ApiUtils.md5
import com.notableFactory.marvelapp.utils.ApiUtils.toHex
import java.util.*

object MarvelCharactersRepository {

    suspend fun fetchCharacters(): List<SuperHero> {
        val timeStamp = Date().time.toString()
        val characters = MarvelClient.service
            .listCharacters(
                apiKey = "29cf241c04f2f38cdc390afd5d901eea",
                ts = timeStamp,
                hash = "$timeStamp${"c9d33219994e7b7dc4aa75e54ef7b5c5b41d2351"}${"29cf241c04f2f38cdc390afd5d901eea"}".md5().toHex()
            )

        return CharacterNetworkMapper.fromGetCharactersResponse(characters)
    }
}
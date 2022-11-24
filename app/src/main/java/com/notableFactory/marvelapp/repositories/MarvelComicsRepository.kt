package com.notableFactory.marvelapp.repositories

import com.notableFactory.marvelapp.model.MarvelComic
import com.notableFactory.marvelapp.utils.ApiUtils.md5
import com.notableFactory.marvelapp.utils.ApiUtils.toHex
import java.util.*

object MarvelComicsRepository {

    suspend fun fetchCharacterComics(characterId: String): MutableList<MarvelComic> {
        val timeStamp = Date().time.toString()
        val characters = MarvelClient.service
            .listCharacterComics(
                apiKey = "29cf241c04f2f38cdc390afd5d901eea",
                ts = timeStamp,
                hash = "$timeStamp${"c9d33219994e7b7dc4aa75e54ef7b5c5b41d2351"}${"29cf241c04f2f38cdc390afd5d901eea"}".md5().toHex(),
                characterId = characterId
            )

        return ComicsNetworkMapper.fromGetCharacterComicsResponse(characters)

    }

}
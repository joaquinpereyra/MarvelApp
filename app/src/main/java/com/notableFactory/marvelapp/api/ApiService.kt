package com.notableFactory.marvelapp.api

import com.notableFactory.marvelapp.model.CharactersResponse
import com.notableFactory.marvelapp.model.ComicsResponse
import com.notableFactory.marvelapp.model.SuperHero
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("characters")
    suspend fun listCharacters(
        @Query("apikey") apiKey: String,
        @Query("ts") ts: String,
        @Query("hash") hash: String,

    ): CharactersResponse

    @GET("characters")
    suspend fun listCharacterStartWith(
        @Query("apikey") apiKey: String,
        @Query("ts") ts: String,
        @Query("hash") hash: String,
        @Query("nameStartsWith") name: String,
    ) : CharactersResponse

    @GET("characters/{characterId}/comics")
    suspend fun listCharacterComics(
        @Path("characterId") characterId: String,
        @Query("apikey") apiKey: String,
        @Query("ts") ts: String,
        @Query("hash") hash: String,
    ) : ComicsResponse

}
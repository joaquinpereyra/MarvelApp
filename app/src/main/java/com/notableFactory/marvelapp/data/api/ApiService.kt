package com.notableFactory.marvelapp.data.api

import com.notableFactory.marvelapp.R
import com.notableFactory.marvelapp.data.api.model.CharactersResponse
import retrofit2.http.GET
import retrofit2.http.Query
import java.sql.Timestamp

interface ApiService {

    @GET("characters")
    suspend fun getAllCharacters(
        @Query("apikey") apiKey: String,
        @Query("orderBy") orderBy: String,
        @Query("ts") ts: String,
        @Query("hash") hash: String,
    ): CharactersResponse

}
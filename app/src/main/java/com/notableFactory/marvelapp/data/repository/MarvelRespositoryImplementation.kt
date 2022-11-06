package com.notableFactory.marvelapp.data.repository

import com.notableFactory.marvelapp.R
import com.notableFactory.marvelapp.data.api.ApiService
import com.notableFactory.marvelapp.data.api.MarvelClient
import com.notableFactory.marvelapp.data.api.model.CharactersResponse
import com.notableFactory.marvelapp.domain.repository.MarvelRepository
import com.notableFactory.marvelapp.extensions.md5
import com.notableFactory.marvelapp.extensions.toHex
import java.sql.Timestamp
import java.util.*
import javax.inject.Inject

class MarvelRespositoryImplementation @Inject constructor(
    private val api:ApiService
):MarvelRepository{
    override suspend fun getAllCharacter(offset: Int): CharactersResponse {
        val timeStamp = Date().time.toString()
        return api.getAllCharacters(
            apiKey = R.string.PUBLIC_KEY.toString(),
            orderBy = "-modified",
            ts = timeStamp,
            hash = "$timeStamp${R.string.PRIVATE_KEY}${R.string.PUBLIC_KEY}".md5().toHex()
        )
    }
}
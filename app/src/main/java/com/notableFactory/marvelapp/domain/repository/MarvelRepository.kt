package com.notableFactory.marvelapp.domain.repository

import com.notableFactory.marvelapp.data.api.model.CharactersResponse


interface MarvelRepository {
    suspend fun getAllCharacter(offset:Int):CharactersResponse
}
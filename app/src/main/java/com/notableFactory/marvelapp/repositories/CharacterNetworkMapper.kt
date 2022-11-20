package com.notableFactory.marvelapp.repositories

import com.notableFactory.marvelapp.model.CharactersResponse
import com.notableFactory.marvelapp.model.SuperHero
import com.notableFactory.marvelapp.model.Result

object CharacterNetworkMapper: EntityMapper<Result, SuperHero> {

    override fun mapFromEntity(entity: Result): SuperHero {
        return entity.toCharacter()
    }

    //Not needed for this sample
    override fun mapToEntity(domainModel: SuperHero): Result {
        TODO("Not yet implemented")
    }

    fun fromGetCharactersResponse(networkResponse: CharactersResponse): MutableList<SuperHero> {
        return networkResponse.data.results.map { mapFromEntity(it) }.toMutableList()
    }
}
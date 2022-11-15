package com.notableFactory.marvelapp.repositories

import com.notableFactory.marvelapp.model.CharactersResponse
import com.notableFactory.marvelapp.model.SuperHero
import com.notableFactory.marvelapp.model.Result

object CharacterNetworkMapper: EntityMapper<Result, SuperHero> {

    override fun mapFromEntity(entity: Result): SuperHero {
        return SuperHero(
            id = entity.id,
            name = entity.name,
            description = entity.description,
            thumbnailUrl = "${entity.thumbnail.path}${entity.thumbnail.extension}"
        )
    }

    //Not needed for this sample
    override fun mapToEntity(domainModel: SuperHero): Result {
        TODO("Not yet implemented")
    }

    fun fromGetCharactersResponse(networkResponse: CharactersResponse): List<SuperHero> {
        return networkResponse.data.results.map { mapFromEntity(it) }
    }
}
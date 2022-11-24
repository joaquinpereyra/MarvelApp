package com.notableFactory.marvelapp.repositories

import com.notableFactory.marvelapp.model.*

object ComicsNetworkMapper : EntityMapper<ComicResult, MarvelComic> {
    override fun mapFromEntity(entity: ComicResult): MarvelComic {
        return entity.toCharacter()
    }

    override fun mapToEntity(domainModel: MarvelComic): ComicResult {
        TODO("Not yet implemented")
    }

    fun fromGetCharacterComicsResponse(networkResponse: ComicsResponse): MutableList<MarvelComic> {
        return networkResponse.data.results.map { mapFromEntity(it) }.toMutableList()
    }
}
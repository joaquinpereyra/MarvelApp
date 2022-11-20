package com.notableFactory.marvelapp.model

data class Result(
    val comics: Comics,
    val description: String,
    val events: Events,
    val id: String,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val series: Series,
    val stories: Stories,
    val thumbnail: Thumbnail,
    val urls: List<Url>
) {
    fun toCharacter():SuperHero {
        return SuperHero(
            id = id,
            name = name,
            description = description,
            thumbnailUrl = thumbnail.path,
            thumbnailExt = thumbnail.extension,
            comics = comics.items.map {
                it.name
            }
        )
    }
}
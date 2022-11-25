package com.notableFactory.marvelapp.model

import com.notableFactory.marvelapp.R

data class ComicResult(
    val title: String,
    val issueNumber: Int,
    val thumbnail: Thumbnail,
    val characters: CharactersList,
    val description: String?
)
{
    fun toComic():MarvelComic {
        return MarvelComic(
            title = this.title,
            issueNumber = issueNumber.toString(),
            description = this.description ?: R.string.comic_default_description.toString(),
            thumbnailUrlExt= thumbnail.extension,
            thumbnailUrlPath= thumbnail.path,
            characters = characters.getCharactersNames()
        )
    }
}

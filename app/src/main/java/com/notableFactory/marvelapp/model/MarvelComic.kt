package com.notableFactory.marvelapp.model

data class MarvelComic(
    val title: String,
    val issueNumber: String,
    val description: String,
    val thumbnailUrlExt: String,
    val thumbnailUrlPath: String,
    val characters: List<String>
)

package com.notableFactory.marvelapp.model

data class ComicsData(
    val count: String,
    val limit: String,
    val offset: String,
    val results: List<ComicResult>,
    val total: String
)

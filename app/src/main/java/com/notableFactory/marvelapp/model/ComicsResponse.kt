package com.notableFactory.marvelapp.model

data class ComicsResponse(
    val attributionHTML: String,
    val attributionText: String,
    val code: String,
    val copyright: String,
    val `data`: ComicsData,
    val etag: String,
    val status: String
)

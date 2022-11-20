package com.notableFactory.marvelapp.model

data class SuperHero(
    val id: String,
    val name: String,
    val description: String,
    val thumbnailUrl: String,
    val thumbnailExt: String,
    val comics: List<String>
)
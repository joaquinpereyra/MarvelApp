package com.notableFactory.marvelapp.model

import java.io.Serializable

data class SuperHero(
    val id: String,
    val name: String,
    val description: String,
    val thumbnailUrl: String,
    val thumbnailExt: String,
    val comics: List<String>
) : Serializable
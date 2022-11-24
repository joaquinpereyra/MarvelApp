package com.notableFactory.marvelapp.model

data class CharactersList(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: List<CharacterSummary>

) {
    fun getCharactersNames(): List<String> {
        val names = items.map {
            it.name
        }
        return names
    }
}


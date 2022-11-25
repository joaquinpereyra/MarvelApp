package com.notableFactory.marvelapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.notableFactory.marvelapp.model.MarvelComic
import com.notableFactory.marvelapp.repositories.MarvelComicsRepository
import kotlinx.coroutines.launch

class SuperHeroViewModel : ViewModel() {

    private val _comicsList = MutableLiveData<List<MarvelComic>>()
    val comicsList: LiveData<List<MarvelComic>> = _comicsList


    fun fetchCharacterComics(characterId: String) {
        viewModelScope.launch {

            val comicsResponse = MarvelComicsRepository.fetchCharacterComics(characterId)

            if (comicsResponse != null) {
                _comicsList.value = comicsResponse
            }
        }
    }
}
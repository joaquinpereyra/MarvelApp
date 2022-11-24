package com.notableFactory.marvelapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.notableFactory.marvelapp.R
import com.notableFactory.marvelapp.model.MarvelComic
import com.notableFactory.marvelapp.model.SuperHero
import com.notableFactory.marvelapp.repositories.MarvelCharactersRepository
import com.notableFactory.marvelapp.repositories.MarvelComicsRepository
import com.notableFactory.marvelapp.ui.adapters.HeroesListAdapter
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {

    private val _heroesList = MutableLiveData<List<SuperHero>>()
    val heroesList: LiveData<List<SuperHero>> = _heroesList

    private val _comicsList = MutableLiveData<List<MarvelComic>>()
    val comicsList: LiveData<List<MarvelComic>> = _comicsList

    init {
        fetchCharacters()
    }


     fun fetchCharacters() {
        viewModelScope.launch {
            val charactersResponse = MarvelCharactersRepository.fetchCharacters()

            if (charactersResponse != null) {
                _heroesList.value = charactersResponse
            }
        }
    }

    fun searchByNameStartWith(name: String){
        viewModelScope.launch {
            val charactersResponse = MarvelCharactersRepository.fetchCharactersStartWith(name)

            if (charactersResponse != null) {
                _heroesList.value = charactersResponse
            }
        }
    }

    fun fetchCharacterComics(characterId: String) {
        viewModelScope.launch {

            val comicsResponse = MarvelComicsRepository.fetchCharacterComics(characterId)

            if (comicsResponse != null) {
                _comicsList.value = comicsResponse
            }
        }
    }

}
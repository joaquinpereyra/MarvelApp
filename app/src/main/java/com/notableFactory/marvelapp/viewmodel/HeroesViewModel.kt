package com.notableFactory.marvelapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.notableFactory.marvelapp.model.SuperHero
import com.notableFactory.marvelapp.repositories.MarvelCharactersRepository
import com.notableFactory.marvelapp.repositories.MarvelClient
import com.notableFactory.marvelapp.utils.ApiUtils
import kotlinx.coroutines.launch

class HeroesViewModel : ViewModel() {

    private val _heroesList = MutableLiveData<List<SuperHero>>()
    val heroesList: MutableLiveData<List<SuperHero>> = _heroesList

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
}
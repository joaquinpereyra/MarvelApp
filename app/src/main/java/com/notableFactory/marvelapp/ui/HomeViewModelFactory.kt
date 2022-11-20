package com.notableFactory.marvelapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.notableFactory.marvelapp.repositories.MarvelCharactersRepository
import com.notableFactory.marvelapp.viewmodel.HomeViewModel

class HomeViewModelFactory (
        private val repository: MarvelCharactersRepository
    ) : ViewModelProvider.NewInstanceFactory(){

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel() as T
        }
}
package com.notableFactory.marvelapp.di

import com.notableFactory.marvelapp.viewmodel.HomeViewModel
import com.notableFactory.marvelapp.viewmodel.SuperHeroViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val viewModelsModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::SuperHeroViewModel)
}

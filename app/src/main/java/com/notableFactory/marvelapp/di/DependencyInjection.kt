package com.notableFactory.marvelapp.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class DependencyInjection : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@DependencyInjection)
            modules(listOf(viewModelsModule))

        }
    }

}
package com.vincenzopavano.discounttracker.injection.component

import android.app.Application
import android.content.Context
import dagger.Component
import com.vincenzopavano.discounttracker.data.DataManager
import com.vincenzopavano.discounttracker.data.remote.PokemonApi
import com.vincenzopavano.discounttracker.injection.ApplicationContext
import com.vincenzopavano.discounttracker.injection.module.AppModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    @ApplicationContext
    fun context(): Context

    fun application(): Application

    fun dataManager(): DataManager

    fun pokemonApi(): PokemonApi
}

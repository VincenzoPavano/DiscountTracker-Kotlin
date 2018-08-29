package com.vincenzopavano.discounttracker.features.main

import com.vincenzopavano.discounttracker.features.base.MvpView

interface MainMvpView : MvpView {

    fun showPokemon(pokemon: List<String>)

    fun showProgress(show: Boolean)

    fun showError(error: Throwable)

}
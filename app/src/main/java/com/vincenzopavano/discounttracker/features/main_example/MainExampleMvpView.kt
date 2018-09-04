package com.vincenzopavano.discounttracker.features.main_example

import com.vincenzopavano.discounttracker.features.base.MvpView

interface MainExampleMvpView : MvpView {

    fun showPokemon(pokemon: List<String>)

    fun showProgress(show: Boolean)

    fun showError(error: Throwable)

}
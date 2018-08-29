package com.vincenzopavano.discounttracker.features.detail

import com.vincenzopavano.discounttracker.data.model.Pokemon
import com.vincenzopavano.discounttracker.data.model.Statistic
import com.vincenzopavano.discounttracker.features.base.MvpView

interface DetailMvpView : MvpView {

    fun showPokemon(pokemon: Pokemon)

    fun showStat(statistic: Statistic)

    fun showProgress(show: Boolean)

    fun showError(error: Throwable)

}
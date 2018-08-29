package com.vincenzopavano.discounttracker.features.main

import com.vincenzopavano.discounttracker.data.DataManager
import com.vincenzopavano.discounttracker.features.base.BasePresenter
import com.vincenzopavano.discounttracker.injection.ConfigPersistent
import com.vincenzopavano.discounttracker.util.rx.scheduler.SchedulerUtils
import javax.inject.Inject

@ConfigPersistent
class MainPresenter @Inject
constructor(private val dataManager: DataManager) : BasePresenter<MainMvpView>() {

    fun getPokemon(limit: Int) {
        checkViewAttached()
        mvpView?.showProgress(true)
        dataManager.getPokemonList(limit)
                .compose(SchedulerUtils.ioToMain<List<String>>())
                .subscribe({ pokemons ->
                    mvpView?.apply {
                        showProgress(false)
                        showPokemon(pokemons)
                    }
                }) { throwable ->
                    mvpView?.apply {
                        showProgress(false)
                        showError(throwable)
                    }
                }
    }
}
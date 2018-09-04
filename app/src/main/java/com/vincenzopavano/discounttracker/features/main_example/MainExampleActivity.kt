package com.vincenzopavano.discounttracker.features.main_example

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.vincenzopavano.discounttracker.R
import com.vincenzopavano.discounttracker.features.base.BaseActivity
import com.vincenzopavano.discounttracker.features.common.ErrorView
import com.vincenzopavano.discounttracker.util.gone
import com.vincenzopavano.discounttracker.util.visible
import kotlinx.android.synthetic.main.activity_mainexample.*
import timber.log.Timber
import javax.inject.Inject


class MainExampleActivity : BaseActivity(), MainExampleMvpView, PokemonAdapter.ClickListener, ErrorView.ErrorListener {

    @Inject
    lateinit var pokemonAdapter: PokemonAdapter
    @Inject
    lateinit var mainExamplePresenter: MainExamplePresenter

    companion object {
        private val POKEMON_COUNT = 20
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent().inject(this)
        mainExamplePresenter.attachView(this)

        setSupportActionBar(main_toolbar)
        swipeToRefresh_?.apply {
            setProgressBackgroundColorSchemeResource(R.color.primary)
            setColorSchemeResources(R.color.white)
            setOnRefreshListener { mainExamplePresenter.getPokemon(POKEMON_COUNT) }
        }

        pokemonAdapter.setClickListener(this)
        recyclerPokemon?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = pokemonAdapter
        }

        viewError?.setErrorListener(this)

        mainExamplePresenter.getPokemon(POKEMON_COUNT)
    }

    override fun layoutId() = R.layout.activity_mainexample

    override fun onDestroy() {
        super.onDestroy()
        mainExamplePresenter.detachView()
    }

    override fun showPokemon(pokemon: List<String>) {
        pokemonAdapter.apply {
            setPokemon(pokemon)
            notifyDataSetChanged()
        }

        recyclerPokemon?.visible()
        swipeToRefresh_?.visible()
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            if (recyclerPokemon?.visibility == View.VISIBLE && pokemonAdapter.itemCount > 0) {
                swipeToRefresh_?.isRefreshing = true
            } else {
                progressBar?.visible()
                recyclerPokemon?.gone()
                swipeToRefresh_?.gone()
            }

            viewError?.gone()
        } else {
            swipeToRefresh_?.isRefreshing = false
            progressBar?.gone()
        }
    }

    override fun showError(error: Throwable) {
        recyclerPokemon?.gone()
        swipeToRefresh_?.gone()
        viewError?.visible()
        Timber.e(error, "There was an error retrieving the pokemon")
    }

    override fun onPokemonClick(pokemon: String) {
        //startActivity(DetailActivity.getStartIntent(this, pokemon))
    }

    override fun onReloadData() {
        mainExamplePresenter.getPokemon(POKEMON_COUNT)
    }

}
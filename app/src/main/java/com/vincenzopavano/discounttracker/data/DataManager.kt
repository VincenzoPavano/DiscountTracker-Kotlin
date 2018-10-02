package com.vincenzopavano.discounttracker.data

import com.vincenzopavano.discounttracker.data.model.Discount
import com.vincenzopavano.discounttracker.data.model.Pokemon
import com.vincenzopavano.discounttracker.data.remote.DiscountApi
import com.vincenzopavano.discounttracker.data.remote.PokemonApi
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager @Inject
constructor(private val discountApi: DiscountApi, private val pokemonApi: PokemonApi) {

    fun getDiscountList(): Single<List<Discount>> {
        return discountApi.getDiscountList()
    }

    fun getPokemonList(limit: Int): Single<List<String>> {
        return pokemonApi.getPokemonList(limit)
                .toObservable()
                .flatMapIterable { (results) -> results }
                .map { (name) -> name }
                .toList()
    }

    fun getPokemon(name: String): Single<Pokemon> {
        return pokemonApi.getPokemon(name)
    }
}
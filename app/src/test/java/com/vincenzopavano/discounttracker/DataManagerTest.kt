package com.vincenzopavano.discounttracker

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.vincenzopavano.discounttracker.common.TestDataFactory
import com.vincenzopavano.discounttracker.data.DataManager
import com.vincenzopavano.discounttracker.data.model.PokemonListResponse
import com.vincenzopavano.discounttracker.data.remote.PokemonApi
import com.vincenzopavano.discounttracker.util.RxSchedulersOverrideRule
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DataManagerTest {

    @Rule @JvmField val overrideSchedulersRule = RxSchedulersOverrideRule()
    val namedResourceList = TestDataFactory.makeNamedResourceList(5)
    val pokemonListResponse = PokemonListResponse(namedResourceList)
    val name = "charmander"
    val pokemon = TestDataFactory.makePokemon(name)

    val mockPokemonApi: PokemonApi = mock {
        on { getPokemonList(anyInt()) } doReturn Single.just(pokemonListResponse)
        on { getPokemon(anyString()) } doReturn Single.just(pokemon)
    }

    private var dataManager = DataManager(mockPokemonApi)

    @Test
    fun getPokemonListCompletesAndEmitsPokemonList() {
        dataManager.getPokemonList(10)
                .test()
                .assertComplete()
                .assertValue(TestDataFactory.makePokemonNameList(namedResourceList))
    }

    @Test
    fun getPokemonCompletesAndEmitsPokemon() {
        dataManager.getPokemon(name)
                .test()
                .assertComplete()
                .assertValue(pokemon)
    }
}

package com.vincenzopavano.discounttracker

import com.nhaarman.mockito_kotlin.*
import com.vincenzopavano.discounttracker.common.TestDataFactory
import com.vincenzopavano.discounttracker.data.DataManager
import com.vincenzopavano.discounttracker.features.main_example.MainExampleMvpView
import com.vincenzopavano.discounttracker.features.main_example.MainExamplePresenter
import com.vincenzopavano.discounttracker.util.RxSchedulersOverrideRule
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyBoolean
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by ravindra on 24/12/16.
 */
@RunWith(MockitoJUnitRunner::class)
class MainExamplePresenterTest {

    val pokemonList = TestDataFactory.makePokemonNamesList(10)

    val mockMainExampleMvpView: MainExampleMvpView = mock()
    val mockDataManager: DataManager = mock {
        on { getPokemonList(10) } doReturn Single.just(pokemonList)
        on { getPokemonList(5) } doReturn Single.error<List<String>>(RuntimeException())
    }
    private var mainExamplePresenter: MainExamplePresenter? = null

    @JvmField
    @Rule
    val overrideSchedulersRule = RxSchedulersOverrideRule()

    @Before
    fun setUp() {
        mainExamplePresenter = MainExamplePresenter(mockDataManager)
        mainExamplePresenter?.attachView(mockMainExampleMvpView)
    }

    @After
    fun tearDown() {
        mainExamplePresenter?.detachView()
    }

    @Test
    @Throws(Exception::class)
    fun getPokemonReturnsPokemonNames() {

        mainExamplePresenter?.getPokemon(10)

        verify(mockMainExampleMvpView, times(2)).showProgress(anyBoolean())
        verify(mockMainExampleMvpView).showPokemon(pokemonList)
        verify(mockMainExampleMvpView, never()).showError(RuntimeException())

    }

    @Test
    @Throws(Exception::class)
    fun getPokemonReturnsError() {

        mainExamplePresenter?.getPokemon(5)

        verify(mockMainExampleMvpView, times(2)).showProgress(anyBoolean())
        verify(mockMainExampleMvpView).showError(any())
        verify(mockMainExampleMvpView, never()).showPokemon(any())
    }
}
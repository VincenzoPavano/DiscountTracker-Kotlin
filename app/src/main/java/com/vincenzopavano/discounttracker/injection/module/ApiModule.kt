package com.vincenzopavano.discounttracker.injection.module

import com.vincenzopavano.discounttracker.data.remote.DiscountApi
import dagger.Module
import dagger.Provides
import com.vincenzopavano.discounttracker.data.remote.PokemonApi
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = arrayOf(NetworkModule::class))
class ApiModule {

    @Provides
    @Singleton
    internal fun providePokemonApi(retrofit: Retrofit): PokemonApi =
            retrofit.create(PokemonApi::class.java)

    @Provides
    @Singleton
    internal fun provideDiscountApi(retrofit: Retrofit): DiscountApi =
            retrofit.create(DiscountApi::class.java)
}
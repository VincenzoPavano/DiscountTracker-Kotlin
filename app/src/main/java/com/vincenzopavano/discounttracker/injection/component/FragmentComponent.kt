package com.vincenzopavano.discounttracker.injection.component

import com.vincenzopavano.discounttracker.injection.PerFragment
import com.vincenzopavano.discounttracker.injection.module.FragmentModule
import dagger.Subcomponent

/**
 * This component inject dependencies to all Fragments across the application
 */
@PerFragment
@Subcomponent(modules = [FragmentModule::class])
interface FragmentComponent
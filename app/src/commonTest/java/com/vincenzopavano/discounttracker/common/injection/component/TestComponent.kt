package com.vincenzopavano.discounttracker.common.injection.component

import dagger.Component
import com.vincenzopavano.discounttracker.common.injection.module.ApplicationTestModule
import com.vincenzopavano.discounttracker.injection.component.AppComponent
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationTestModule::class))
interface TestComponent : AppComponent
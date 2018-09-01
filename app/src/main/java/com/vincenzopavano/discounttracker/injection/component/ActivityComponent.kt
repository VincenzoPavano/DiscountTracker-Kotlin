package com.vincenzopavano.discounttracker.injection.component

import com.vincenzopavano.discounttracker.injection.PerActivity
import com.vincenzopavano.discounttracker.injection.module.ActivityModule
import com.vincenzopavano.discounttracker.features.base.BaseActivity
import com.vincenzopavano.discounttracker.features.main.MainActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(baseActivity: BaseActivity)

    fun inject(mainActivity: MainActivity)
}

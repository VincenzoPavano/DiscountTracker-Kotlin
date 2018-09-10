package com.vincenzopavano.discounttracker.features.main

import com.vincenzopavano.discounttracker.data.model.Discount
import com.vincenzopavano.discounttracker.features.base.MvpView

interface MainMvpView : MvpView {

    fun showDiscount(discounts: Discount)

    fun showProgress(show: Boolean)

    fun showError(error: Throwable)

}
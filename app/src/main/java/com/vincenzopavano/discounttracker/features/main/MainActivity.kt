package com.vincenzopavano.discounttracker.features.main

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


class MainActivity : BaseActivity(), MainMvpView {
    @Inject
    lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent().inject(this)
        mainPresenter.attachView(this)
    }

    override fun layoutId() = R.layout.activity_main

    override fun onDestroy() {
        super.onDestroy()
        mainPresenter.detachView()
    }

    override fun showProgress(show: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(error: Throwable) {

    }
}
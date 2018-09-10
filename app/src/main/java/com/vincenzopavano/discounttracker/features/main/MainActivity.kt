package com.vincenzopavano.discounttracker.features.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.vincenzopavano.discounttracker.R
import com.vincenzopavano.discounttracker.data.model.Discount
import com.vincenzopavano.discounttracker.features.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity(), MainMvpView, MainAdapter.ClickListener {
    @Inject
    lateinit var mainPresenter: MainPresenter

    @Inject
    lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent().inject(this)
        mainPresenter.attachView(this)

        // Swipe to refresh
        swipeToRefresh?.apply {
            setProgressBackgroundColorSchemeResource(R.color.primary)
            setColorSchemeResources(R.color.white)
            setOnRefreshListener {
                // Call presenter to get discount list
                mainPresenter.getDiscount()
            }
        }

        // RecyclerView and adapter
        mainAdapter.setClickListener(this)
        recyclerDiscount?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mainAdapter
        }

        // Call presenter to get initial list
        mainPresenter.getDiscount()
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

    override fun onDiscountClick(discount: Discount) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
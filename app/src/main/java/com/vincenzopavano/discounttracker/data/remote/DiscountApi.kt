package com.vincenzopavano.discounttracker.data.remote

import com.vincenzopavano.discounttracker.data.model.DiscountListResponse
import io.reactivex.Single
import retrofit2.http.GET

interface DiscountApi {

    @GET("discount")
    fun getDiscountList() : Single<DiscountListResponse>
}
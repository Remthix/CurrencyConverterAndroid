package com.sdremthix.currencyconverter.data.api

import com.sdremthix.currencyconverter.data.models.CurrencyResponseModel
import io.reactivex.Single
import retrofit2.http.GET

interface CurrencyEndpoint {

    @GET("latest/")
    fun fetchCurrencyList(): Single<CurrencyResponseModel>
}
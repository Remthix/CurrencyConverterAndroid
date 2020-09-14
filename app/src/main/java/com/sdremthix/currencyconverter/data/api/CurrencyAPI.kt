package com.sdremthix.currencyconverter.data.api

import com.sdremthix.currencyconverter.data.models.CurrencyResponseModel
import io.reactivex.Single
import javax.inject.Inject

class CurrencyAPI @Inject constructor(private val currencyEndpoint: CurrencyEndpoint) {

    fun fetchCurrencyList(): Single<CurrencyResponseModel> =
        currencyEndpoint.fetchCurrencyList()

}
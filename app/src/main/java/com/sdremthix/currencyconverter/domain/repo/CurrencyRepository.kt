package com.sdremthix.currencyconverter.domain.repo

import com.sdremthix.currencyconverter.domain.models.CurrencyDisplayModel
import io.reactivex.Single

interface CurrencyRepository {

    fun fetchCurrencyList(): Single<List<CurrencyDisplayModel>>
}
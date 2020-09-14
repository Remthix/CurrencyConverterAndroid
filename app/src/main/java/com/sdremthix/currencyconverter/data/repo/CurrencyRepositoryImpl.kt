package com.sdremthix.currencyconverter.data.repo

import com.sdremthix.currencyconverter.data.CurrencyAdapter
import com.sdremthix.currencyconverter.data.api.CurrencyAPI
import com.sdremthix.currencyconverter.domain.models.CurrencyDisplayModel
import com.sdremthix.currencyconverter.domain.repo.CurrencyRepository
import io.reactivex.Single

/**
 * Obtains data from the API
 */
class CurrencyRepositoryImpl(
    private val currencyAPI: CurrencyAPI,
    private val currencyAdapter: CurrencyAdapter
) : CurrencyRepository {

    override fun fetchCurrencyList(): Single<List<CurrencyDisplayModel>> {
        return currencyAPI.fetchCurrencyList().map { currencyAdapter.map(it) }
    }
}
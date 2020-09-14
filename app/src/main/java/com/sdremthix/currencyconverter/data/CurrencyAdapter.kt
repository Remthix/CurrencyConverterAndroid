package com.sdremthix.currencyconverter.data

import com.sdremthix.currencyconverter.data.models.CurrencyResponseModel
import com.sdremthix.currencyconverter.domain.models.CurrencyDisplayModel
import javax.inject.Inject

/**
 * Map the raw response data holder into the display model.
 */
class CurrencyAdapter @Inject constructor() {
    fun map(currencyResponse: CurrencyResponseModel): List<CurrencyDisplayModel> {
        return currencyResponse.rates.map { mapSingleItem(it) }
    }

    fun mapSingleItem(rateEntry: Map.Entry<String, Double>): CurrencyDisplayModel {
        return CurrencyDisplayModel(name = rateEntry.key, symbol = rateEntry.key)
    }


}
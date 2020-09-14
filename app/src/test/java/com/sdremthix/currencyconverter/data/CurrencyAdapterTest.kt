package com.sdremthix.currencyconverter.data

import com.sdremthix.currencyconverter.data.models.CurrencyResponseModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CurrencyAdapterTest {

    private lateinit var currencyAdapter: CurrencyAdapter
    private lateinit var currencyResponseModel: CurrencyResponseModel

    @Before
    fun setUp() {
        currencyAdapter = CurrencyAdapter()
        currencyResponseModel = CurrencyResponseModel(
            true, 1387929599, "EUR", "2020-09-14", mapOf(
                "USD" to 1.636492,
                "EUR" to 1.196476,
                "CAD" to 1.739516
            )
        )
    }

    @Test
    fun returnsListOfDisplayModels() {
        val result = currencyAdapter.map(currencyResponseModel)

        assertEquals(result.size, 3)
    }

    @Test
    fun valuesMatch() {
        val result = currencyAdapter.map(currencyResponseModel)

        assertEquals("USD", result[0].symbol)
        assertEquals("EUR", result[1].symbol)
        assertEquals("CAD", result[2].symbol)
    }
}
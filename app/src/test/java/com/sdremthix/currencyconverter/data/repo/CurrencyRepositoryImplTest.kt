package com.sdremthix.currencyconverter.data.repo

import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.sdremthix.currencyconverter.data.CurrencyAdapter
import com.sdremthix.currencyconverter.data.api.CurrencyAPI
import com.sdremthix.currencyconverter.data.models.CurrencyResponseModel
import com.sdremthix.currencyconverter.domain.models.CurrencyDisplayModel
import com.sdremthix.currencyconverter.domain.repo.CurrencyRepository
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Testing the repository implementation.
 */
@RunWith(MockitoJUnitRunner::class)
class CurrencyRepositoryImplTest {

    @Mock
    lateinit var currencyAPI: CurrencyAPI

    @Mock
    lateinit var currencyAdapter: CurrencyAdapter

    private lateinit var currencyRepository: CurrencyRepository

    @Before
    fun setUp() {
        currencyRepository = CurrencyRepositoryImpl(currencyAPI, currencyAdapter)
    }

    @Test
    fun `gets the list of available currency rates`() {

        given(currencyAPI.fetchCurrencyList()).willReturn(Single.just(mock()))
        given(currencyAdapter.map(getResponseTestData())).willReturn(mock())
        currencyRepository.fetchCurrencyList().test()

        verify(currencyAPI).fetchCurrencyList()
    }

    @Test
    fun `mocks the list of available currency rates and returns a list of currencies`() {
        val mockResponse = getResponseTestData()
        val mockDisplayModelList = arrayListOf<CurrencyDisplayModel>(mock())
        given(currencyAPI.fetchCurrencyList()).willReturn(Single.just(mockResponse))
        given(currencyAdapter.map(getResponseTestData())).willReturn(mock())
        currencyRepository.fetchCurrencyList().test()

        verify(currencyAdapter).map(eq(mockResponse))
    }

    @Test
    fun `returns correct mapped list given the api response`() {
        val mockResponse = getResponseTestData()
        val mockDisplayModelList = arrayListOf(
            CurrencyDisplayModel("USD", "USD"),
            CurrencyDisplayModel("EUR", "EUR"),
            CurrencyDisplayModel("CAD", "CAD"),
        )
        given(currencyAPI.fetchCurrencyList()).willReturn(Single.just(mockResponse))
        given(currencyAdapter.map(mockResponse)).willReturn(mockDisplayModelList)
        currencyRepository.fetchCurrencyList().test().assertValue(mockDisplayModelList)

        assertEquals(currencyAdapter.map(mockResponse), mockDisplayModelList)
    }

    @Test
    fun `returns wrongly mapped list given the api response`() {
        val mockResponse = getResponseTestData()
        val mockDisplayModelListCorrect = arrayListOf(
            CurrencyDisplayModel("USD", "USD"),
            CurrencyDisplayModel("EUR", "EUR"),
            CurrencyDisplayModel("CAD", "CAD"),
        )

        val mockDisplayModelListWrong = arrayListOf(
            CurrencyDisplayModel("USD", "USD"),
            CurrencyDisplayModel("EUR", "EUR"),
            CurrencyDisplayModel("CD", "CAD"),
        )
        given(currencyAPI.fetchCurrencyList()).willReturn(Single.just(mockResponse))
        given(currencyAdapter.map(mockResponse)).willReturn(mockDisplayModelListWrong)

        assertNotEquals(currencyAdapter.map(mockResponse), mockDisplayModelListCorrect)
    }

    @Test
    fun `response data invokes and exception`() {
        val error = Throwable()
        given(currencyAPI.fetchCurrencyList()).willReturn(Single.error(error))
        currencyRepository.fetchCurrencyList().test().assertError(error)
    }

    /**
     * Mock response data.
     */
    private fun getResponseTestData(): CurrencyResponseModel = CurrencyResponseModel(
        true, 1387929599, "EUR", "2020-09-14", mapOf(
            "USD" to 1.636492,
            "EUR" to 1.196476,
            "CAD" to 1.739516
        )
    )
}
package com.sdremthix.currencyconverter.domain.usecase

import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.sdremthix.currencyconverter.domain.RxJavaTestHooksResetRule
import com.sdremthix.currencyconverter.domain.models.CurrencyDisplayModel
import com.sdremthix.currencyconverter.domain.repo.CurrencyRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetCurrencyListUseCaseTest {

    @get:Rule
    var rxJavaTestHooksResetRule = RxJavaTestHooksResetRule()

    @Mock
    lateinit var currencyRepository: CurrencyRepository

    private lateinit var getCurrencyListUseCase: GetCurrencyListUseCase


    @Before
    fun setUp() {

        getCurrencyListUseCase = GetCurrencyListUseCase(currencyRepository)
    }

    @Test
    fun displayLoadingOnStart() {
        given(currencyRepository.fetchCurrencyList()).willReturn(Single.just(mock()))
        getCurrencyListUseCase.fetchCurrencyList().test()
            .assertValueAt(0) { it == GetCurrencyListUseCase.Result.Loading }
    }

    @Test
    fun getCurrencyListFail() {
        given(currencyRepository.fetchCurrencyList()).willReturn(Single.error(Throwable()))
        getCurrencyListUseCase.fetchCurrencyList().test()
        verify(currencyRepository).fetchCurrencyList()
    }

    @Test
    fun getCurrencyListFailWithThrowable() {
        val throwable = Throwable()
        given(currencyRepository.fetchCurrencyList()).willReturn(Single.error(throwable))
        getCurrencyListUseCase.fetchCurrencyList().test()
            .assertValueAt(1) { (it as GetCurrencyListUseCase.Result.Fail).throwable == throwable }
    }

    @Test
    fun getCurrencyListSuccess() {
//        val mockCurrencyResponse = CurrencyResponseModel(
//            true, 1387929599, "EUR", "2020-09-14", mapOf(
//                "USD" to 1.636492,
//                "EUR" to 1.196476,
//                "CAD" to 1.739516
//            )
//        )
        val mockCurrencyDisplayModelList = arrayListOf<CurrencyDisplayModel>()
        given(currencyRepository.fetchCurrencyList()).willReturn(
            Single.just(
                mockCurrencyDisplayModelList
            )
        )

        getCurrencyListUseCase.fetchCurrencyList().test()
            .assertValueAt(1) { (it as GetCurrencyListUseCase.Result.Success).currencyList == mockCurrencyDisplayModelList }
    }
}
package com.sdremthix.currencyconverter.domain.usecase

import com.sdremthix.currencyconverter.domain.models.CurrencyDisplayModel
import com.sdremthix.currencyconverter.domain.repo.CurrencyRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Obtains a list of currency objects and returns its values in a sealed Result object.
 */
class GetCurrencyListUseCase @Inject constructor(private val currencyRepo: CurrencyRepository) {

    //Result state
    sealed class Result {
        data class Success(val currencyList: List<CurrencyDisplayModel>) : Result()
        data class Fail(val throwable: Throwable) : Result()
        object Loading : Result()
    }

    fun fetchCurrencyList(): Observable<Result> {
        return currencyRepo.fetchCurrencyList()
            .toObservable()
            .map {
                Result.Success(it) as Result
            }
            .onErrorReturn { Result.Fail(it) }
            .startWith(Result.Loading)
    }
}
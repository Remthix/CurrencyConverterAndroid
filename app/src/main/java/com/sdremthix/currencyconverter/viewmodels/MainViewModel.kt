package com.sdremthix.currencyconverter.viewmodels

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableChar
import androidx.lifecycle.ViewModel
import com.sdremthix.currencyconverter.domain.usecase.GetCurrencyListUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Organizes data and the View component state.
 */
class MainViewModel @Inject constructor(private val getCurrencyListUseCase: GetCurrencyListUseCase) :
    ViewModel() {

    private val disposables = CompositeDisposable()
    val currencyList = ObservableArrayList<String>()
    val progressVisibility = ObservableBoolean()
    val errorMessage = ObservableChar()


    fun onBind() {

        //execute call and observe results
        disposables.add(
            getCurrencyListUseCase.fetchCurrencyList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { handleGetCurrencyResult(it) })

    }

    fun onDestroy() {
        disposables.clear()
    }

    private fun handleGetCurrencyResult(result: GetCurrencyListUseCase.Result) {
        progressVisibility.set(result == GetCurrencyListUseCase.Result.Loading)
        when (result) {
            is GetCurrencyListUseCase.Result.Success -> {
                currencyList.addAll(result.currencyList.map { it.name })
            }
            is GetCurrencyListUseCase.Result.Fail -> {
            }
        }
    }

    private fun fetchCurrencyList() {

    }
}
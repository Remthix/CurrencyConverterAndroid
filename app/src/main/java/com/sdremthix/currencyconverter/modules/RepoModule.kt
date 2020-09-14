package com.sdremthix.currencyconverter.modules

import com.sdremthix.currencyconverter.data.CurrencyAdapter
import com.sdremthix.currencyconverter.data.api.CurrencyAPI
import com.sdremthix.currencyconverter.data.repo.CurrencyRepositoryImpl
import com.sdremthix.currencyconverter.domain.repo.CurrencyRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Provides
    @Singleton
    fun providesCurrencyRepository(
        currencyAPI: CurrencyAPI,
        currencyAdapter: CurrencyAdapter
    ): CurrencyRepository {
        return CurrencyRepositoryImpl(currencyAPI, currencyAdapter)
    }
}
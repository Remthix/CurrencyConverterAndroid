package com.sdremthix.currencyconverter.modules

import com.sdremthix.currencyconverter.data.api.CurrencyEndpoint
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class EndpointModule {

    @Provides
    @Singleton
    fun providesRetrofitEndpoint(retrofit: Retrofit): CurrencyEndpoint {
        return retrofit.create(CurrencyEndpoint::class.java)
    }
}
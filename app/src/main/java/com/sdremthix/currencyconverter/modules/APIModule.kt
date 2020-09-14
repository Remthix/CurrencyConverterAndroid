package com.sdremthix.currencyconverter.modules

import com.google.gson.Gson
import com.sdremthix.currencyconverter.data.api.CurrencyEndpoint
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class APIModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("https://data.fixer.io/api/").build()
    }

    @Provides
    @Singleton
    fun providesRetrofitEndpoint(retrofit: Retrofit): CurrencyEndpoint {
        return retrofit.create(CurrencyEndpoint::class.java)
    }
}
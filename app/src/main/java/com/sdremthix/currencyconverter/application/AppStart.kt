package com.sdremthix.currencyconverter.application

import android.app.Application
import com.sdremthix.currencyconverter.components.ApplicationComponent
import com.sdremthix.currencyconverter.components.DaggerApplicationComponent

/**
 * The app starting point.
 * Used for initial properties setup and initialization.
 */
class AppStart : Application() {

    val applicationComponent: ApplicationComponent = DaggerApplicationComponent.create()

    override fun onCreate() {
        super.onCreate()

    }
}
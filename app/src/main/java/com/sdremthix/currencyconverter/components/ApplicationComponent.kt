package com.sdremthix.currencyconverter.components

import com.sdremthix.currencyconverter.modules.APIModule
import com.sdremthix.currencyconverter.ui.activities.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [APIModule::class])
interface APIComponent {

    fun bind(activity: MainActivity)
}
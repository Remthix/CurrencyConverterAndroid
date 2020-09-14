package com.sdremthix.currencyconverter.components

import com.sdremthix.currencyconverter.modules.APIModule
import com.sdremthix.currencyconverter.modules.RepoModule
import com.sdremthix.currencyconverter.ui.activities.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [APIModule::class, RepoModule::class])
interface ApplicationComponent {

    fun bind(activity: MainActivity)
}
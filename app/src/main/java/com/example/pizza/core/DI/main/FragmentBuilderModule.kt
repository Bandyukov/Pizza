package com.example.pizza.core.DI.main

import com.example.pizza.ui.screens.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeMainFragment() : MainFragment
}
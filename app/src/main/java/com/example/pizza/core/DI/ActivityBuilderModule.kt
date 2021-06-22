package com.example.pizza.core.DI

import com.example.pizza.ui.activities.MainActivity
import com.example.pizza.core.DI.main.FragmentBuilderModule
import com.example.pizza.core.DI.main.MainScope
import com.example.pizza.core.DI.main.ViewModelBuilderModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(
        modules = [FragmentBuilderModule::class, ViewModelBuilderModule::class]
    )
    @MainScope
    abstract fun contributeMainActivity() : MainActivity
}
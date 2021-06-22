package com.example.pizza.core.DI

import com.example.pizza.MainActivity
import com.example.pizza.core.DI.main.FragmentBuilderModule
import com.example.pizza.core.DI.main.MainModule
import com.example.pizza.core.DI.main.MainScope
import com.example.pizza.core.DI.main.ViewModelBuilderModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(
        modules = [MainModule::class, FragmentBuilderModule::class, ViewModelBuilderModule::class]
    )
    @MainScope
    abstract fun contributeMainActivity() : MainActivity
}
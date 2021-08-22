package com.example.pizza.core.DI.viewModel

import androidx.lifecycle.ViewModelProvider
import com.example.lib_architecture.viewmodel.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory) :
            ViewModelProvider.Factory
}
package com.example.pizza.core.DI.main

import androidx.lifecycle.ViewModel
import com.example.pizza.core.DI.viewModel.ViewModelKey
import com.example.pizza.ui.screens.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelBuilderModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel) : ViewModel

}
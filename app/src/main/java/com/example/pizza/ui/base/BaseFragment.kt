package com.example.pizza.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pizza.core.DI.AppComponent
import com.example.pizza.core.DI.viewModel.ViewModelProviderFactory
import javax.inject.Inject

abstract class BaseFragment<T: ViewModel> : Fragment() {

    //@Inject
    //private lateinit var factory: ViewModelProviderFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}
package com.example.pizza

import com.example.core_network.DI.NetworkComponent
import com.example.pizza.core.DI.AppComponent

object DI {
    lateinit var appComponent: AppComponent
    internal set

    lateinit var networkComponent: NetworkComponent
    internal set
}
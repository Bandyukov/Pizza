package com.example.pizza

import com.example.core_network.DI.DaggerNetworkComponent
import com.example.pizza.core.DI.AppComponent
import com.example.pizza.core.DI.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = initDI()

    private fun initDI(): AppComponent {
        DI.networkComponent = DaggerNetworkComponent.create()
        DI.appComponent = DaggerAppComponent.builder()
            .application(this)
            .datasource(DI.networkComponent.datasource())
            .build()
        return DI.appComponent
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}
package com.example.pizza.core.DI

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.core_network.datasource.MealsRemoteDataSource
import com.example.pizza.App
import com.example.pizza.core.DI.resources.ResourceProvider
import com.example.pizza.core.DI.resources.ResourcesModule
import com.example.pizza.core.DI.viewModel.ViewModelFactoryModule
import com.example.pizza.ui.base.BaseFragment
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBuilderModule::class,
        ViewModelFactoryModule::class,
        ResourcesModule::class
    ]
)
@Singleton
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun datasource(remoteDataSource: MealsRemoteDataSource) : Builder

        fun build(): AppComponent
    }
}
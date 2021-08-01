package com.example.core_network.DI

import com.example.core_network.datasource.MealsRemoteDataSource
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class])
@Singleton
interface NetworkComponent {
    fun datasource() : MealsRemoteDataSource
}

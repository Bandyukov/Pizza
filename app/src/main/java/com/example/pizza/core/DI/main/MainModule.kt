package com.example.pizza.core.DI.main

import com.example.pizza.core.DB.MealDao
import com.example.pizza.core.network.MealsApi
import com.example.pizza.core.repository.Repository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import java.lang.StringBuilder

@Module
abstract class MainModule {
    companion object {
        @Provides
        fun provideAPI(retrofit: Retrofit): MealsApi = retrofit.create(MealsApi::class.java)

        @Provides
        fun provideRepository(mealsApi: MealsApi, mealDao: MealDao): Repository =
            Repository(mealsApi, mealDao)
    }
}
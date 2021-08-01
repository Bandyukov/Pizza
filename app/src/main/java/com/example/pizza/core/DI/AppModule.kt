package com.example.pizza.core.DI

import android.app.Application
import com.example.pizza.core.DB.MealDao
import com.example.pizza.core.DB.MealDatabase
import com.example.pizza.core.preferences.AppPreferences
import com.example.pizza.core.repository.Repository
import com.example.pizza.core.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindRepository(repositoryImpl: RepositoryImpl) : Repository

    companion object {
        @Provides
        @Singleton
        fun provideDAO(application: Application): MealDao =
            MealDatabase.getInstance(application).mealDao

        @Provides
        @Singleton
        fun provideAppPreferences(application: Application) : AppPreferences =
            AppPreferences.getInstance(application)
    }

}
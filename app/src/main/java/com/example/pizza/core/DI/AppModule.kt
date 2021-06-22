package com.example.pizza.core.DI

import android.app.Application
import com.example.pizza.core.DB.MealDao
import com.example.pizza.core.DB.MealDatabase
import com.example.pizza.core.network.MealsApi
import com.example.pizza.core.preferences.AppPreferences
import com.example.pizza.core.repository.Repository
import com.example.pizza.core.repository.RepositoryImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindRepository(repositoryImpl: RepositoryImpl) : Repository

    companion object {
        const val BASE_URL = "https://www.themealdb.com/"

        @Provides
        @Singleton
        fun provideMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        @Provides
        @Singleton
        fun provideRetrofit(moshi: Moshi): Retrofit =
            Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(BASE_URL)
                .build()

        @Provides
        @Singleton
        fun provideDAO(application: Application): MealDao =
            MealDatabase.getInstance(application).mealDao

        @Provides
        @Singleton
        fun provideAppPreferences(application: Application) : AppPreferences =
            AppPreferences.getInstance(application)

        @Provides
        @Singleton
        fun provideAPI(retrofit: Retrofit): MealsApi = retrofit.create(MealsApi::class.java)
    }

}
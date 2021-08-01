package com.example.core_network.DI

import com.example.core_network.api.MealsApi
import com.example.core_network.datasource.MealsRemoteDataSource
import com.example.core_network.datasource.MealsRemoteDataSourceImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
abstract class NetworkModule {
    companion object {
        const val BASE_URL = "https://www.themealdb.com/"

        @Provides
        @Singleton
        fun provideMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        @Provides
        @Singleton
        fun provideApi(moshi: Moshi): MealsApi =
            Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(BASE_URL)
                .build()
                .create(MealsApi::class.java)

    }

    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(source: MealsRemoteDataSourceImpl) : MealsRemoteDataSource
}
package com.example.core_network.datasource

import com.example.core_network.api.MealVO
import kotlinx.coroutines.flow.Flow

interface MealsRemoteDataSource {
    fun getMealsFromNet(category: String) : Flow<List<MealVO>>
}
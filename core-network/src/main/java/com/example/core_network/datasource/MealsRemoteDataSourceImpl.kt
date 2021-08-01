package com.example.core_network.datasource

import com.example.core_network.api.MealVO
import com.example.core_network.api.MealsApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MealsRemoteDataSourceImpl @Inject constructor(private val mealsApi: MealsApi) :
    MealsRemoteDataSource {
    override fun getMealsFromNet(category: String): Flow<List<MealVO>> =
        flow { emit(mealsApi.getMealsByCategory(1, category).meals) }
}
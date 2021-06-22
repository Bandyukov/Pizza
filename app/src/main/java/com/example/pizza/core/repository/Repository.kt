package com.example.pizza.core.repository

import com.example.pizza.core.models.Category
import com.example.pizza.core.models.Meal
import com.example.pizza.core.network.MealVO
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getMeals(category: Category) : List<Meal>
    suspend fun getMealsFromNet(category: Category) : Flow<List<MealVO>>
    fun getLastCategory() : String?
}
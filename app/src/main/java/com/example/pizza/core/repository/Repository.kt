package com.example.pizza.core.repository

import com.example.pizza.core.models.Category
import com.example.pizza.core.models.Meal

interface Repository {
    suspend fun getMeals(category: Category) : List<Meal>
    fun getLastCategory() : String?
}
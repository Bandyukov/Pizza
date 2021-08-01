package com.example.pizza.core.repository

import androidx.lifecycle.LiveData
import com.example.pizza.core.models.Category
import com.example.pizza.core.models.Meal

interface Repository {
    suspend fun getMeals(category: Category)
    fun getLastCategory() : String?
    fun observeDB(): LiveData<List<Meal>>
}
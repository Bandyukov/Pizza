package com.example.pizza.core.mapping

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.pizza.core.DB.MealDB
import com.example.pizza.core.models.Meal
import com.example.pizza.core.network.MealVO

fun MealVO.toMeal() : Meal = Meal(id, title, imagePath, price)

fun MealVO.toMealDB() : MealDB = MealDB(id, title, imagePath, price)

fun MealDB.toMeal() : Meal = Meal(id, title, imagePath, price)

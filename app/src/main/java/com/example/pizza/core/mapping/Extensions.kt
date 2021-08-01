package com.example.pizza.core.mapping

import com.example.core_network.api.MealVO
import com.example.pizza.core.DB.MealDB
import com.example.pizza.core.models.Meal

fun MealVO.toMeal() : Meal = Meal(id, title, imagePath, price)

fun MealVO.toMealDB() : MealDB = MealDB(id, title, imagePath, price)

fun MealDB.toMeal() : Meal = Meal(id, title, imagePath, price)

fun List<MealDB>.toMealList() : List<Meal> = this.map { it.toMeal() }

fun Meal.toMealDB() : MealDB = MealDB(id, title, imagePath, price)

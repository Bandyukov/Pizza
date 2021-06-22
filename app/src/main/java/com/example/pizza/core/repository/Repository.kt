package com.example.pizza.core.repository

import android.util.Log
import com.example.pizza.core.DB.MealDao
import com.example.pizza.core.mapping.toMeal
import com.example.pizza.core.mapping.toMealDB
import com.example.pizza.core.models.Meal
import com.example.pizza.core.network.MealVO
import com.example.pizza.core.network.MealsApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class Repository(private val mealsApi: MealsApi, private val mealDao: MealDao) {

    suspend fun getMeals() : List<Meal> {

        var meals = listOf<Meal>()

        // Вызываем get запрос
        getMealsFromNet()
            .catch {
                // Если получаем ощибку, то покажем пользователю данные из локальной базы
                val mealsDB = mealDao.getAllMealsFromDB()
                meals = mealsDB.map { it.toMeal() }
                Log.i("zxcv", meals.size.toString())
            }
            .collect { mealsVO ->
                // Если все хорошо, то кэшируем полученные данные и показывем их пользователю
                val mealsDB = mealsVO.map { it.toMealDB() }

                // Предварительно очистим базу данных от старых элементов
                mealDao.deleteAllMealsFromDB()
                mealDao.insertMealsIntoDB(mealsDB)
                meals = mealsVO.map { it.toMeal() }
            }

        return meals
    }

    private suspend fun getMealsFromNet() : Flow<List<MealVO>> =
        flow { emit(mealsApi.getMealsByCategory(1, "Beef").meals) }
}
package com.example.pizza.core.repository

import com.example.pizza.core.DB.MealDao
import com.example.pizza.core.mapping.toMeal
import com.example.pizza.core.mapping.toMealDB
import com.example.pizza.core.models.Category
import com.example.pizza.core.models.Meal
import com.example.pizza.core.network.MealVO
import com.example.pizza.core.network.MealsApi
import com.example.pizza.core.preferences.AppPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class Repository(
    private val mealsApi: MealsApi,
    private val mealDao: MealDao,
    private val appPreferences: AppPreferences,
) {

    suspend fun getMeals(category: Category): List<Meal> {

        var meals = listOf<Meal>()

        // Вызываем get запрос
        getMealsFromNet(category)
            .catch {
                // Если получаем ощибку, то покажем пользователю данные из локальной базы
                val mealsDB = mealDao.getAllMealsFromDB()
                meals = mealsDB.map { it.toMeal() }
            }
            .collect { mealsVO ->
                // Если все хорошо, то кэшируем полученные данные и показывем их пользователю
                val mealsDB = mealsVO.map { it.toMealDB() }

                // Предварительно очистим базу данных от старых элементов
                mealDao.deleteAllMealsFromDB()

                mealDao.insertMealsIntoDB(mealsDB)
                meals = mealsVO.map { it.toMeal() }

                // Запомним текущую категорию
                appPreferences.setLastCategory(category.name)
            }

        return meals
    }

    private suspend fun getMealsFromNet(category: Category): Flow<List<MealVO>> =
        flow { emit(mealsApi.getMealsByCategory(1, category.name).meals) }

    fun getLastCategory() : String? = appPreferences.getLastCategory()
}
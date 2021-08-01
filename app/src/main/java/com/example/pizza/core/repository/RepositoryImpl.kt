package com.example.pizza.core.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.core_network.datasource.MealsRemoteDataSource
import com.example.pizza.core.DB.MealDao
import com.example.pizza.core.mapping.toMealDB
import com.example.pizza.core.mapping.toMealList
import com.example.pizza.core.models.Category
import com.example.pizza.core.models.Meal
import com.example.pizza.core.preferences.AppPreferences
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val dataSource: MealsRemoteDataSource,
    private val mealDao: MealDao,
    private val appPreferences: AppPreferences,
) : Repository {

    override suspend fun getMeals(category: Category) =
        dataSource.getMealsFromNet(category.name)
            .catch {
                // Если получаем ощибку, то покажем пользователю данные из локальной базы
//                val mealsDB = mealDao.getAllMealsFromDB()
//                meals = mealsDB.map { it.toMeal() }
                processError()
            }
            .collect { mealsVO ->
                // Если все хорошо, то кэшируем полученные данные и показывем их пользователю
                val mealsDB = mealsVO.map { it.toMealDB() }

                // Предварительно очистим базу данных от старых элементов
                mealDao.deleteAllMealsFromDB()

                mealDao.insertMealsIntoDB(mealsDB)

                // Запомним текущую категорию
                appPreferences.setLastCategory(category.name)
            }



    override fun getLastCategory(): String? = appPreferences.getLastCategory()

    override fun observeDB(): LiveData<List<Meal>> = mealDao.getAllMealsFromDB().map {
        it.toMealList()
    }

    private fun processError() {
        Log.i("zxcv", "Error")
    }
}

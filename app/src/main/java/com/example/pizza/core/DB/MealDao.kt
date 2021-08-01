package com.example.pizza.core.DB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {

    @Query("SELECT * FROM meals_table")
    fun getAllMealsFromDB() : LiveData<List<MealDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMealsIntoDB(meals: List<MealDB>)

    @Query("DELETE FROM meals_table")
    suspend fun deleteAllMealsFromDB()
}
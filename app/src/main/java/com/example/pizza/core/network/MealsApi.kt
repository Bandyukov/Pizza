package com.example.pizza.core.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MealsApi {
    @GET("/api/json/v1/{key}/filter.php/")
    suspend fun getMealsByCategory(@Path("key") key: Int, @Query("c") category: String): Response
}
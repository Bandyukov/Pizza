package com.example.pizza.core.network

import com.squareup.moshi.Json

data class Response(
    @Json(name = "meals")
    val meals: List<MealVO>
)
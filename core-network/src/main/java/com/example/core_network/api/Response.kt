package com.example.core_network.api

import com.squareup.moshi.Json

data class Response(
    @Json(name = "meals")
    val meals: List<MealVO>
)
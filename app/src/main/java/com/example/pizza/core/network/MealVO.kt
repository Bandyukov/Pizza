package com.example.pizza.core.network

import com.squareup.moshi.Json


data class MealVO(

    @Json(name = "idMeal")
    val id: Int,

    @Json(name = "strMeal")
    val title: String,

    @Json(name = "strMealThumb")
    val imagePath: String,

    val price: Int = (100..999).random()

)
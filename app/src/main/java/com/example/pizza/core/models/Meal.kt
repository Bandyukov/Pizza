package com.example.pizza.core.models

import com.example.pizza.core.base.ListItem

data class Meal(
    val id: Int,
    val title: String,
    val imagePath: String,
    val price: Int,
) : ListItem {
    override val itemId: Int
        get() = id
}
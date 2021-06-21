package com.example.pizza.core.models

import com.example.pizza.core.base.ListItem

data class Food(
    val title: String,
    val info: String,
    val price: String,
    val imagePath: String? = null,
) : ListItem {
    override val itemId: Int
        get() = title.hashCode() + price.hashCode()
}
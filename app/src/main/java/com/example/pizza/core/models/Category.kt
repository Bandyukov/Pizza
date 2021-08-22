package com.example.pizza.core.models

import com.example.lib_architecture.base.ListItem

data class Category(
    val name: String,
    var isChecked: Boolean = false
) : ListItem {
    override val itemId: Int
        get() = name.hashCode()
}
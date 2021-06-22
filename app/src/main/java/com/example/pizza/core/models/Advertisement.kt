package com.example.pizza.core.models

import android.graphics.drawable.Drawable
import com.example.pizza.core.base.ListItem

data class Advertisement(
    val image: Drawable?,
) : ListItem {
    override val itemId: Int
        get() = image.hashCode()
}
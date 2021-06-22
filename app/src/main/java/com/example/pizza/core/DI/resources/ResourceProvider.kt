package com.example.pizza.core.DI.resources

import android.graphics.drawable.Drawable
import androidx.annotation.ArrayRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface ResourceProvider {
    fun string(@StringRes id: Int) : String
    fun array(@ArrayRes id: Int) : Array<String>
    fun drawable(@DrawableRes id: Int) : Drawable
}
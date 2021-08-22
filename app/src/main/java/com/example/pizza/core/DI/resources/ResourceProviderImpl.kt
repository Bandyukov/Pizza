package com.example.pizza.core.DI.resources

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import com.example.pizza.core.mapping.getColorCompat
import com.example.pizza.core.mapping.getDrawableCompat
import javax.inject.Inject

@SuppressLint("UseCompatLoadingForDrawables")
class ResourceProviderImpl @Inject constructor(private val context: Context) :ResourceProvider {
    override fun string(id: Int): String = context.resources.getString(id)

    override fun array(id: Int): Array<String> = context.resources.getStringArray(id)

    override fun drawable(id: Int): Drawable = context.getDrawableCompat(id)

    override fun color(id: Int): Int = context.getColorCompat(id)
}
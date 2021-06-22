package com.example.pizza.core.DI.resources

import android.annotation.SuppressLint
import android.app.Application
import android.graphics.drawable.Drawable
import javax.inject.Inject

@SuppressLint("UseCompatLoadingForDrawables")
class ResourceProviderImpl @Inject constructor(private val application: Application) :ResourceProvider {
    override fun string(id: Int): String = application.resources.getString(id)

    override fun array(id: Int): Array<String> = application.resources.getStringArray(id)

    override fun drawable(id: Int): Drawable = application.resources.getDrawable(id)
}
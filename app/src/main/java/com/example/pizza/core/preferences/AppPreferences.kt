package com.example.pizza.core.preferences

import android.content.Context
import android.content.SharedPreferences

// Здесь будем хранить текущую категорию продуктов
class AppPreferences private constructor(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun getLastCategory(): String? = sharedPreferences.getString(CATEGORY, null)
    fun setLastCategory(cat: String) = sharedPreferences.edit().putString(CATEGORY, cat).apply()

    companion object {
        const val PREFERENCES_NAME = "AppPreferences"
        const val CATEGORY = "category"

        private var INSTANCE: AppPreferences? = null

        fun getInstance(context: Context): AppPreferences {
            var instance = INSTANCE
            if (instance == null) {
                instance = AppPreferences(context)
                INSTANCE = instance
            }
            return instance
        }
    }
}
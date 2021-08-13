package com.example.pizza.core.mapping

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.example.core_network.api.MealVO
import com.example.pizza.R
import com.example.pizza.core.DB.MealDB
import com.example.pizza.core.models.Meal
import com.example.pizza.ui.activities.MainActivity

fun MealVO.toMeal() : Meal = Meal(id, title, imagePath, price)

fun MealVO.toMealDB() : MealDB = MealDB(id, title, imagePath, price)

fun MealDB.toMeal() : Meal = Meal(id, title, imagePath, price)

fun List<MealDB>.toMealList() : List<Meal> = this.map { it.toMeal() }

fun Meal.toMealDB() : MealDB = MealDB(id, title, imagePath, price)

fun AppWidgetProvider.updateWidget(
    context: Context?,
    appWidgetManager: AppWidgetManager?,
    appWidgetIds: IntArray?) {

    val intent = Intent(context, MainActivity::class.java)
    val pendingIntent : PendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

    context?.let {
        val remoteView = RemoteViews(it.packageName, R.layout.simple_widget_1x1)
        remoteView.setOnClickPendingIntent(R.id.image_view_simple_widget_1x1, pendingIntent)

        appWidgetManager?.updateAppWidget(appWidgetIds, remoteView)
    }
}

fun Fragment.toast(@StringRes stringId: Int) = Toast.makeText(this.context, stringId, Toast.LENGTH_SHORT).show()

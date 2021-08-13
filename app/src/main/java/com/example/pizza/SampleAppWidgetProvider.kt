package com.example.pizza

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import com.example.pizza.core.mapping.updateWidget

class SampleAppWidgetProvider : AppWidgetProvider() {
    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        //super.onUpdate(context, appWidgetManager, appWidgetIds)
        appWidgetIds?.let { widget ->
            widget.map { updateWidget(context, appWidgetManager, appWidgetIds) }
        }
    }
}
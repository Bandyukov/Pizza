package com.example.pizza.ui.customViews

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.example.pizza.R

class MainToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0
) : LinearLayout(context, attrs, defStyleAttrs) {

    init {
        inflate(context, R.layout.toolbar_main, this)
    }
}
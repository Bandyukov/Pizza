package com.example.pizza.core.DB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meals_table")
data class MealDB(
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "imagePath")
    val imagePath: String,

    @ColumnInfo(name = "price")
    val price: Int,
) {
    @PrimaryKey(autoGenerate = true)
    var uniqueId: Int = 0
}
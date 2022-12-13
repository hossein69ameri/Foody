package com.example.nourifoodapp1.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [FoodEntity::class], version = 1, exportSchema = false)
@TypeConverters(RecepieConverter::class)
abstract class FoodDatabase : RoomDatabase() {

    abstract fun dao(): FoodDao
}
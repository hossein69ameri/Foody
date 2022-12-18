package com.example.nourifoodapp1.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.nourifoodapp1.data.database.entity.FavoriteEntity
import com.example.nourifoodapp1.data.database.entity.FoodEntity

@Database(entities = [FoodEntity::class,FavoriteEntity::class], version = 2, exportSchema = false)
@TypeConverters(RecepieConverter::class)
abstract class FoodDatabase : RoomDatabase() {

    abstract fun dao(): FoodDao
}
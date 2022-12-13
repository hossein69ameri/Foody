package com.example.nourifoodapp1.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoodRecepi(foodEntity: FoodEntity)

    @Query("SELECT * FROM TABLE_NAME ORDER BY id ASC")
    fun readRecepies(): Flow<List<FoodEntity>>
}
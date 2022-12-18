package com.example.nourifoodapp1.data.database

import androidx.room.*
import com.example.nourifoodapp1.data.database.entity.FavoriteEntity
import com.example.nourifoodapp1.data.database.entity.FoodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {

    //Dao for HomePage
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoodRecepi(foodEntity: FoodEntity)

    @Query("SELECT * FROM TABLE_NAME ORDER BY id ASC")
    fun readRecepies(): Flow<List<FoodEntity>>


    //Dao for Favorite Page
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteResult(favoriteEntity: FavoriteEntity)

    @Delete
    suspend fun deleteFavoriteResult(favoriteEntity: FavoriteEntity)

    @Query("DELETE FROM table_name_favorite")
    fun deleteAllFavoriteResult()

    @Query("SELECT * FROM TABLE_NAME_FAVORITE ORDER BY id ASC")
    fun readFavoriteResult(): Flow<List<FavoriteEntity>>
}
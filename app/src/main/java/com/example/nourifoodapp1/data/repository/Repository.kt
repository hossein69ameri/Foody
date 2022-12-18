package com.example.nourifoodapp1.data.repository

import com.example.nourifoodapp1.data.database.FoodDao
import com.example.nourifoodapp1.data.database.entity.FavoriteEntity
import com.example.nourifoodapp1.data.database.entity.FoodEntity
import com.example.nourifoodapp1.data.server.FoodRecepieApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(private val foodRecepieApi: FoodRecepieApi, private val foodDao: FoodDao) {

    //retrofit
    suspend fun getRecepies(queries: Map<String, String>) = foodRecepieApi.getRecepies(queries)
    suspend fun searchRecepie(searchRecepi: Map<String, String>) = foodRecepieApi.searchRecepie(searchRecepi)

    //database Home
    fun readRecipes(): Flow<List<FoodEntity>> { return foodDao.readRecepies() }
    suspend fun insertRecipes(recipesEntity: FoodEntity) = foodDao.insertFoodRecepi(recipesEntity)

    //database Favorite
   suspend fun insertFavoriteRecipe(favoriteEntity: FavoriteEntity) = foodDao.insertFavoriteResult(favoriteEntity)
   suspend fun deleteFAvoriteRecipe(favoriteEntity: FavoriteEntity) = foodDao.deleteFavoriteResult(favoriteEntity)
   fun readFavoriteRecipe() : Flow<List<FavoriteEntity>> { return foodDao.readFavoriteResult() }
   fun deleteAllFavoriteRecipe() = foodDao.deleteAllFavoriteResult()
}
package com.example.nourifoodapp1.data.repository

import com.example.nourifoodapp1.data.database.FoodDao
import com.example.nourifoodapp1.data.database.entity.FavoriteEntity
import com.example.nourifoodapp1.data.database.entity.FoodEntity
import com.example.nourifoodapp1.data.database.entity.FoodJokeEntity
import com.example.nourifoodapp1.data.model.FoodJoke
import com.example.nourifoodapp1.data.server.FoodRecepieApi
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val foodRecepieApi: FoodRecepieApi, private val foodDao: FoodDao) {

    //retrofit
    suspend fun getRecepies(queries: Map<String, String>) = foodRecepieApi.getRecepies(queries)
    suspend fun searchRecepie(searchRecepi: Map<String, String>) = foodRecepieApi.searchRecepie(searchRecepi)

    //retrofit foodjoke
    suspend fun getFoodJoke(apiKey: String): Response<FoodJoke> {
        return foodRecepieApi.getFoodJoke(apiKey)
    }

    //database foodjoke
    suspend fun inserFoodJoke(foodJokeEntity: FoodJokeEntity) = foodDao.inserFoodJoke(foodJokeEntity)
    fun readFoodJoke() : Flow<List<FoodJokeEntity>> { return foodDao.readFoodjoke() }

    //database Home
    fun readRecipes(): Flow<List<FoodEntity>> { return foodDao.readRecepies() }
    suspend fun insertRecipes(recipesEntity: FoodEntity) = foodDao.insertFoodRecepi(recipesEntity)

    //database Favorite
   suspend fun insertFavoriteRecipe(favoriteEntity: FavoriteEntity) = foodDao.insertFavoriteResult(favoriteEntity)
   suspend fun deleteFAvoriteRecipe(favoriteEntity: FavoriteEntity) = foodDao.deleteFavoriteResult(favoriteEntity)
   fun readFavoriteRecipe() : Flow<List<FavoriteEntity>> { return foodDao.readFavoriteResult() }
   fun deleteAllFavoriteRecipe() = foodDao.deleteAllFavoriteResult()
}
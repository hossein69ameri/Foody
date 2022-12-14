package com.example.nourifoodapp1.data.repository

import com.example.foody.models.FoodRecipe
import com.example.nourifoodapp1.data.database.FoodDao
import com.example.nourifoodapp1.data.database.FoodEntity
import com.example.nourifoodapp1.data.server.FoodRecepieApi
import com.example.nourifoodapp1.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Repository @Inject constructor(private val foodRecepieApi: FoodRecepieApi, private val foodDao: FoodDao) {

    /** Retrofit */
    suspend fun getRecepies(queries: Map<String, String>) = foodRecepieApi.getRecepies(queries)

    /** Room */
    fun readRecipes(): Flow<List<FoodEntity>> {
        return foodDao.readRecepies()
    }
    suspend fun insertRecipes(recipesEntity: FoodEntity) {
        foodDao.insertFoodRecepi(recipesEntity)
    }
}
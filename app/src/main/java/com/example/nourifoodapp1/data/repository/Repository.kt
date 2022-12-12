package com.example.nourifoodapp1.data.repository

import com.example.foody.models.FoodRecipe
import com.example.nourifoodapp1.data.model.ResponseFood
import com.example.nourifoodapp1.data.server.FoodRecepieApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val foodRecepieApi: FoodRecepieApi) {

    suspend fun getRecepies(queries: Map<String, String>) : Flow<Response<ResponseFood>>{
        return flow {
            emit(foodRecepieApi.getRecepies(queries))
        }
    }

}
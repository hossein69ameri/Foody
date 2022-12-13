package com.example.nourifoodapp1.data.repository

import com.example.nourifoodapp1.data.model.ResponseFood
import com.example.nourifoodapp1.data.server.FoodRecepieApi
import com.example.nourifoodapp1.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Repository @Inject constructor(private val foodRecepieApi: FoodRecepieApi) {

    suspend fun getRecepies(queries: Map<String, String>): Flow<NetworkResult<ResponseFood>> {
        return flow {
            emit(NetworkResult.Loading())
            when (foodRecepieApi.getRecepies(queries).code()) {
                200 -> emit(NetworkResult.Success(foodRecepieApi.getRecepies(queries).body()))
                in 400..402 -> emit(NetworkResult.Error("Error"))
            }
        }.catch { emit(NetworkResult.Error(it.message.toString())) }
    }
}
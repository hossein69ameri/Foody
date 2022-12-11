package com.example.nourifoodapp1.data.server

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val foodRecepieApi: FoodRecepieApi) {

    suspend fun getRecepies(queries: Map<String, String>) = foodRecepieApi.getRecepies(queries)

}
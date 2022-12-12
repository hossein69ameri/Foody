package com.example.nourifoodapp1.data.server

import com.example.foody.models.FoodRecipe
import com.example.nourifoodapp1.data.model.ResponseFood
import com.example.nourifoodapp1.utils.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface FoodRecepieApi {

    @GET("recipes/complexSearch")
    suspend fun getRecepies(@QueryMap queries: Map<String, String>): Response<ResponseFood>
}
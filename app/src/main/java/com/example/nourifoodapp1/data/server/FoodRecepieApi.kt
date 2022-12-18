package com.example.nourifoodapp1.data.server

import com.example.foody.models.FoodRecipe
import com.example.nourifoodapp1.data.model.FoodJoke
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface FoodRecepieApi {

    //get list food recipe
    @GET("recipes/complexSearch")
    suspend fun getRecepies(@QueryMap queries: Map<String, String>): Response<FoodRecipe>

    //search food recipe
    @GET("recipes/complexSearch")
    suspend fun searchRecepie(@QueryMap searchRecepie: Map<String, String>): Response<FoodRecipe>

    //food joke
    @GET("food/jokes/random")
    suspend fun getFoodJoke(@Query("apiKey") apiKey: String): Response<FoodJoke>

}
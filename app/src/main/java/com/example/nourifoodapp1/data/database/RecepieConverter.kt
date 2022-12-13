package com.example.nourifoodapp1.data.database

import androidx.room.TypeConverters
import com.example.foody.models.FoodRecipe
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RecepieConverter {

    var gson = Gson()

    @TypeConverters
    fun foodRecepiToString(foodRecipe: FoodRecipe): String {
        return gson.toJson(foodRecipe)
    }

    @TypeConverters
    fun stringToFoodRecepi(data: String): FoodRecipe {
        val listToken = object : TypeToken<FoodRecipe>() {}.type
        return gson.fromJson(data,listToken)
    }


}
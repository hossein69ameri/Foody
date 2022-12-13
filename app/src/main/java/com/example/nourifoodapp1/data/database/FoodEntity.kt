package com.example.nourifoodapp1.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foody.models.FoodRecipe
import com.example.nourifoodapp1.utils.TABLE_NAME

@Entity(tableName = TABLE_NAME)
class FoodEntity(
    var foodRecipe: FoodRecipe
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}

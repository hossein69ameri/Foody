package com.example.nourifoodapp1.data.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nourifoodapp1.data.model.FoodJoke

@Entity(tableName = "table_foodJoke")
class FoodJokeEntity (
    @Embedded
    val foodJoke: FoodJoke
        ) {
    @PrimaryKey(autoGenerate = false)
    var id : Int = 0
}
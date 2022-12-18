package com.example.nourifoodapp1.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foody.models.Result
import com.example.nourifoodapp1.utils.TABLE_NAME_FAVORITE

@Entity(tableName = TABLE_NAME_FAVORITE)
class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var result: Result
)
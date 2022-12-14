package com.example.nourifoodapp1.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ActivityRetainedScoped
class DataStoreRepository @Inject constructor(@ApplicationContext private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("food_data_store")
        val selectedMealType = stringPreferencesKey("mealType")
        val selectedMealTypeId = intPreferencesKey("mealTypeId")
        val selectedDietType = stringPreferencesKey("dietType")
        val selectedDietTypeId = intPreferencesKey("dietTypeId")
    }

    suspend fun saveMealAndDiet(mealType: String, mealTypeId: Int, dietType: String, dietTypeId: Int) {
        context.dataStore.edit {
            it[selectedMealType] = mealType
            it[selectedMealTypeId] = mealTypeId
            it[selectedDietType] = dietType
            it[selectedDietTypeId] = dietTypeId

        }
    }

    fun getMealAndDiet(): Flow<MealAndDietType> =
        context.dataStore.data.map {
            val selectedMealType = it[selectedMealType] ?: "main course"
            val selectedMealTypeId = it[selectedMealTypeId] ?: 0
            val selectedDietType = it[selectedDietType] ?: "gluten free"
            val selectedDietTypeId = it[selectedDietTypeId] ?: 0
            MealAndDietType(selectedMealType, selectedMealTypeId, selectedDietType, selectedDietTypeId)
        }


    data class MealAndDietType(val selectedMealType: String, val selectedMealTypeId: Int, val selectedDietType: String, val selectedDietTypeId: Int)

}
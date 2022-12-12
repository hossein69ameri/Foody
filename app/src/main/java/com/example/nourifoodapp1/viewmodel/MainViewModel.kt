package com.example.nourifoodapp1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foody.models.FoodRecipe
import com.example.nourifoodapp1.data.model.ResponseFood
import com.example.nourifoodapp1.data.repository.Repository
import com.example.nourifoodapp1.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository,application: Application) : AndroidViewModel(application) {

    val recipesResponse = MutableLiveData<ResponseFood>()

    fun getRecipes(queries: Map<String, String>) = viewModelScope.launch {
        repository.getRecepies(queries).collect{
            recipesResponse.postValue(it.body())
        }
    }

    fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()

        queries[QUERY_NUMBER] = "50"
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_TYPE] = "snack"
        queries[QUERY_DIET] = "vegan"
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"

        return queries
    }

}
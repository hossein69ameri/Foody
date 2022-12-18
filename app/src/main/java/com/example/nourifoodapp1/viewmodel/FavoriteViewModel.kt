package com.example.nourifoodapp1.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.nourifoodapp1.data.database.entity.FavoriteEntity
import com.example.nourifoodapp1.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: Repository,application: Application) : AndroidViewModel(application) {

    val readFavoriteRecipeData : LiveData<List<FavoriteEntity>> = repository.readFavoriteRecipe().asLiveData()


    fun insertFavoriteRecipe(favoriteEntity: FavoriteEntity) = viewModelScope.launch {
        repository.insertFavoriteRecipe(favoriteEntity)
    }
    fun deleteFavoriteRecipe(favoriteEntity: FavoriteEntity) = viewModelScope.launch {
        repository.deleteFAvoriteRecipe(favoriteEntity)
    }
    fun deleteAllFavoriteRecipe() = viewModelScope.launch {
        repository.deleteAllFavoriteRecipe()
    }


}
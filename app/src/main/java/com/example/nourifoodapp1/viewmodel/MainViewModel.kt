package com.example.nourifoodapp1.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foody.models.FoodRecipe
import com.example.nourifoodapp1.data.repository.Repository
import com.example.nourifoodapp1.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    val recipesResponse = MutableLiveData<NetworkResult<FoodRecipe>>()

    fun getRecipes(queries: Map<String, String>) = viewModelScope.launch {
        recipesResponse.value = NetworkResult.Loading()
        if (hasInternetConnection()) {
            val response = repository.remote.getRecepies(queries)
            recipesResponse.value = handleFoodRecipes(response)
        } else {
            recipesResponse.value = NetworkResult.Error("No Internet")
        }

    }




    private fun handleFoodRecipes(response: Response<FoodRecipe>): NetworkResult<FoodRecipe> {
        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResult.Error("TimeOut")
            }
            response.code() == 402 -> {
                return NetworkResult.Error("Error")
            }
            response.body()!!.results.isNullOrEmpty() -> {
                return NetworkResult.Error("Error")
            }
            response.isSuccessful -> {
                val foodRecipe = response.body()
                return NetworkResult.Success(foodRecipe)
            }
            else -> {
                return NetworkResult.Error("Error")
            }
        }
    }
    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }


}
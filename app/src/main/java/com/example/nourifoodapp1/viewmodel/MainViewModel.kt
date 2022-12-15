package com.example.nourifoodapp1.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast
import androidx.lifecycle.*
import com.example.foody.models.FoodRecipe
import com.example.nourifoodapp1.data.database.FoodEntity
import com.example.nourifoodapp1.data.datastore.DataStoreRepository
import com.example.nourifoodapp1.data.repository.Repository
import com.example.nourifoodapp1.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository, application: Application,private val dataStoreRepository: DataStoreRepository) : AndroidViewModel(application) {

    //check interntet
    var networkStatus = false
    fun netWorkConnection(){
        if (!networkStatus){
            Toast.makeText(getApplication(),"No Internet Connection",Toast.LENGTH_SHORT).show()
            saveBackOnline(true)

        }else if (networkStatus){
            if (backOnline){
                Toast.makeText(getApplication(),"Internet In On",Toast.LENGTH_SHORT).show()
                saveBackOnline(false)
            }
        }
    }
    var backOnline = false
    val readBackOnline = dataStoreRepository.readBackOnline.asLiveData()
    fun saveBackOnline(backOnline : Boolean) = viewModelScope.launch(Dispatchers.IO) {
        dataStoreRepository.saveBackOnline(backOnline)
    }

    //retrofit

        //get list
    var recipesResponse = MutableLiveData<NetworkResult<FoodRecipe>>()
    fun getRecipes(queries: Map<String, String>) = viewModelScope.launch {
        getRecipesSafeCall(queries)
    }
    fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()
        viewModelScope.launch {
            readMealAndDiet.collect{
                mealType = it.selectedMealType
                dietType = it.selectedDietType
            }
        }
        queries[QUERY_NUMBER] = "50"
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_TYPE] = mealType
        queries[QUERY_DIET] = dietType
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"

        return queries
    }

        //search list
    var searchData = MutableLiveData<NetworkResult<FoodRecipe>>()
    fun searchResepies(searchResepi: Map<String, String>) = viewModelScope.launch { searchRecepiSafeCall(searchResepi) }
    private suspend fun searchRecepiSafeCall(searchResepi: Map<String, String>) {
        searchData.value = NetworkResult.Loading()
        if (hasInternetConnection()) {
            try {
                val response = repository.searchRecepie(searchResepi)
                searchData.value = handleFoodRecipesResponse(response)
            } catch (e: Exception) {
                searchData.value = NetworkResult.Error("Recipes not found.")
            }
        } else {
            searchData.value = NetworkResult.Error("No Internet Connection.")
        }
    }
    fun applySearchQueries(query : String): HashMap<String, String> {
        val searchQueries: HashMap<String, String> = HashMap()

        searchQueries["query"] = query
        searchQueries[QUERY_NUMBER] = "50"
        searchQueries[QUERY_API_KEY] = API_KEY
        searchQueries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        searchQueries[QUERY_FILL_INGREDIENTS] = "true"

        return searchQueries
    }


    //database
    val readRecipes: LiveData<List<FoodEntity>> = repository.readRecipes().asLiveData()
    private fun insertRecipes(recipesEntity: FoodEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertRecipes(recipesEntity)
        }

    //DataStore
    private var mealType = "main course"
    private var dietType = "gluten free"
    val readMealAndDiet = dataStoreRepository.readMealAndDietType()
    fun saveMealAndDiet(meal : String,mealId : Int,diet : String , dietId : Int) = viewModelScope.launch(Dispatchers.IO) { dataStoreRepository.saveMealAndDiet(meal,mealId,diet,dietId) }


    //other method
    private suspend fun getRecipesSafeCall(queries: Map<String, String>) {
        recipesResponse.value = NetworkResult.Loading()
        if (hasInternetConnection()) {
            try {
                val response = repository.getRecepies(queries)
                recipesResponse.value = handleFoodRecipesResponse(response)

                val foodRecipe = recipesResponse.value!!.data
                if(foodRecipe != null) {
                    offlineCacheRecipes(foodRecipe)
                }
            } catch (e: Exception) {
                recipesResponse.value = NetworkResult.Error("Recipes not found.")
            }
        } else {
            recipesResponse.value = NetworkResult.Error("No Internet Connection.")
        }
    }
    private fun offlineCacheRecipes(foodRecipe: FoodRecipe) {
        val recipesEntity = FoodEntity(foodRecipe)
        insertRecipes(recipesEntity)
    }
    private fun handleFoodRecipesResponse(response: Response<FoodRecipe>): NetworkResult<FoodRecipe> {
        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResult.Error("Timeout")
            }
            response.code() == 402 -> {
                return NetworkResult.Error("API Key Limited.")
            }
            response.body()!!.results.isNullOrEmpty() -> {
                return NetworkResult.Error("Recipes not found.")
            }
            response.isSuccessful -> {
                val foodRecipes = response.body()
                return NetworkResult.Success(foodRecipes!!)
            }
            else -> {
                return NetworkResult.Error(response.message())
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
package com.example.recipeium.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeium.models.Result
import com.example.recipeium.repo.RecipeRepository
import com.example.recipeium.util.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: RecipeRepository) : ViewModel() {

    //region variables
    private var _resultFlow =
        MutableLiveData<NetworkResponse<List<Result>>>(NetworkResponse.Loading)

    val resultFlow: LiveData<NetworkResponse<List<Result>>> get() = _resultFlow

    //endregion

    init {
        viewModelScope.launch {
            val result = repository.getRecipes(prepareQuery())
            Log.d(TAG, "$result")
            result?.let {
                if(result.isNotEmpty())
                _resultFlow.value=NetworkResponse.Success(result)
                else
                    _resultFlow.value=NetworkResponse.Error(Exception("No Data Available"))
            }
            if(result.isNullOrEmpty())
                _resultFlow.value=NetworkResponse.Error(Exception("No Data Available"))

        }



    }

    //region helper functions
    private fun prepareQuery(): HashMap<String, String> {
        var hashmap = HashMap<String, String>()
        hashmap["number"] = "50"
        hashmap["type"] = "finger food"
        hashmap["diet"] = "vegan"
        hashmap["addRecipeInformation"] = "true"
        hashmap["fillIngredients"] = "true"
        return hashmap
    }

    companion object{
        private const val TAG = "HomeViewModel"
    }
//endregion
}
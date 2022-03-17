package com.example.recipeium.repo

import android.util.Log
import com.example.recipeium.data.remote.ApiService
import com.example.recipeium.models.Result
import com.example.recipeium.util.NetworkResponse
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class RecipeRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getRecipes(quires: Map<String, String>): List<Result>? {
        Log.d(TAG, "getRecipes: entered")
       return apiService.getRecipes(quires).body()?.results
    }

    private  val TAG = "RecipeRepository"
}
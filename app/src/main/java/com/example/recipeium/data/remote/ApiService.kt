package com.example.recipeium.data.remote

import com.example.recipeium.models.Recipe
import com.example.recipeium.models.Result
import com.example.recipeium.util.NetworkResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {
    @GET("recipes/complexSearch")
    suspend fun getRecipes(@QueryMap options: Map<String, String>): Response<Recipe>


}
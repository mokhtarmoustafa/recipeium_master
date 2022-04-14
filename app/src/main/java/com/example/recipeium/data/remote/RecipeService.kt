package com.example.recipeium.data.remote

import com.example.recipeium.model.Recipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface RecipeService {
    @GET("recipes/complexSearch")
    suspend fun getRecipes(@QueryMap map: Map<String, String>): Response<Recipe>
}
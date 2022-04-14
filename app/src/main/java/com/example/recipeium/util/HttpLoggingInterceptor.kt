package com.example.recipeium.util

import okhttp3.Interceptor
import okhttp3.Response

class HttpLoggingInterceptorHelper:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newHttpUrl = originalRequest.url.newBuilder()
            .addQueryParameter("apiKey", API_KEY)
            .build()
        val newRequest = originalRequest.newBuilder()
            .url(newHttpUrl)
            .build()
       return  chain.proceed(newRequest)
    }
}
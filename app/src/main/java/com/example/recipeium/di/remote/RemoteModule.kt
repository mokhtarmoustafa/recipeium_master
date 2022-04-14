package com.example.recipeium.di.remote

import com.example.recipeium.BuildConfig
import com.example.recipeium.data.remote.RecipeService
import com.example.recipeium.util.HttpLoggingInterceptorHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Provides
    fun getGsonConverter(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun getOkHttp(): OkHttpClient.Builder {
        val logger = HttpLoggingInterceptor()
        logger.setLevel(HttpLoggingInterceptor.Level.BODY)
        if (BuildConfig.DEBUG) {
            logger.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logger.level = HttpLoggingInterceptor.Level.NONE
        }

        return OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptorHelper())
            .addNetworkInterceptor(logger)
    }

    @Provides
    fun getRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit.Builder {
        return Retrofit.Builder().client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
    }


    @Provides
    fun getService(retrofit: Retrofit): RecipeService {
        return retrofit.create(RecipeService::class.java)
    }

}
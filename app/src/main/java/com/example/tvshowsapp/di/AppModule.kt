package com.example.tvshowsapp.di

import com.example.tvshowsapp.api.ApiService
import com.example.tvshowsapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = Constants.Base_Url

    @Provides
    @Singleton
    fun provideRetrofitInstance(Base_Url:String):ApiService =

        Retrofit.Builder()
            .baseUrl(Base_Url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)



}
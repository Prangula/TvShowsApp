package com.example.tvshowsapp.api

import com.example.tvshowsapp.model.TvShowResponse
import com.example.tvshowsapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(Constants.End_Point)

    suspend fun getTvShows():Response<TvShowResponse>
}
package com.example.tvshowsapp.api

import com.example.tvshowsapp.modelcast.CastResponse
import com.example.tvshowsapp.modelsearch.SearchShowResponse
import com.example.tvshowsapp.modelshow.TvShowResponse
import com.example.tvshowsapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(Constants.END_POINT_SHOWS)
    suspend fun getTvShows():Response<TvShowResponse>

    @GET(Constants.END_POINT_SEARCH)
    suspend fun searchTvShows(
        @Query("q") query:String
    ):Response<SearchShowResponse>

    @GET(Constants.END_POINT_CAST)
    suspend fun showCasts(
        @Path("showId") showId:Int
    ):Response<CastResponse>
}
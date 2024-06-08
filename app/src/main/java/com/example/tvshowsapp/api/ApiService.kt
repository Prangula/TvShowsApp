package com.example.tvshowsapp.api

import com.example.tvshowsapp.models.modelcast.CastResponse
import com.example.tvshowsapp.models.modelepisode.EpisodeResponse
import com.example.tvshowsapp.models.modelsearch.SearchShowResponse
import com.example.tvshowsapp.models.modelshow.TvShowResponse
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

    @GET(Constants.END_POINT_EPISODE)
    suspend fun getShowEpisodes(
        @Path("id") showId:Int
    ):Response<EpisodeResponse>

}
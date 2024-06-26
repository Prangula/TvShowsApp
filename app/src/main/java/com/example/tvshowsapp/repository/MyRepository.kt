package com.example.tvshowsapp.repository

import com.example.tvshowsapp.api.ApiService
import javax.inject.Inject

class MyRepository@Inject constructor(private val apiService: ApiService){

    suspend fun getTvShows() = apiService.getTvShows()
    suspend fun searchTvShows(query:String) = apiService.searchTvShows(query)

    suspend fun showCasts(showId:Int) = apiService.showCasts(showId)

    suspend fun getShowEpisodes(showId: Int) = apiService.getShowEpisodes(showId)

}
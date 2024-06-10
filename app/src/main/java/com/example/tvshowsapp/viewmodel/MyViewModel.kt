package com.example.tvshowsapp.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tvshowsapp.models.modelcast.CastResponseItem
import com.example.tvshowsapp.models.modelepisode.EpisodeResponseItem
import com.example.tvshowsapp.models.modelsearch.SearchShowResponseItem
import com.example.tvshowsapp.models.modelshow.ShowResponseItem
import com.example.tvshowsapp.repository.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject


@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class MyViewModel@Inject constructor(private val myRepository: MyRepository):ViewModel() {

    private val _tvShowResponse = MutableLiveData<List<ShowResponseItem>>()
    val tvShowResponse:LiveData<List<ShowResponseItem>> get() = _tvShowResponse

    private val _searchTvShowResponse = MutableLiveData<List<SearchShowResponseItem>>()
    val searchTvShowResponse:LiveData<List<SearchShowResponseItem>> get() = _searchTvShowResponse

    private val _showCastsResponse = MutableLiveData<List<CastResponseItem>> ()
     val showCastsResponse:MutableLiveData<List<CastResponseItem>> get () = _showCastsResponse

    private val _showEpisodesResponse = MutableLiveData<List<EpisodeResponseItem>>()
    val showEpisodesResponse:LiveData<List<EpisodeResponseItem>> get() = _showEpisodesResponse
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading


    init {
        getAllTvShows()
    }


    private fun getAllTvShows() = viewModelScope.launch {
        _isLoading.postValue(true)
        myRepository.getTvShows().let { response ->
            if (response.isSuccessful) {
                _tvShowResponse.postValue(response.body())
            }
            _isLoading.postValue(false) // Set to false regardless of success or failure
        }
    }

     fun showCasts(showId:Int) = viewModelScope.launch {
        _isLoading.postValue(true)
        myRepository.showCasts(showId).let { response->
            if(response.isSuccessful){
                _showCastsResponse.postValue(response.body())
            }
            _isLoading.postValue(false)

        }

    }

    fun searchAllTvShows(query:String) = viewModelScope.launch {

        _isLoading.postValue(true)
        myRepository.searchTvShows(query).let { response->

            if(response.isSuccessful){
                _searchTvShowResponse.postValue(response.body())
            }
            _isLoading.postValue(false)

        }


    }


    fun showEpisodes(showId:Int) = viewModelScope.launch {

        _isLoading.postValue(true)
        myRepository.getShowEpisodes(showId).let { response->
            if(response.isSuccessful){
                _showEpisodesResponse.postValue(response.body())
            }
            _isLoading.postValue(false)
        }

    }


}
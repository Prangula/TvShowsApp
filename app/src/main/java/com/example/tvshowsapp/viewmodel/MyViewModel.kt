package com.example.tvshowsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tvshowsapp.modelsearch.SearchShowResponseItem
import com.example.tvshowsapp.modelshow.TvShowResponseItem
import com.example.tvshowsapp.repository.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MyViewModel@Inject constructor(private val myRepository: MyRepository):ViewModel() {

    private val _tvShowResponse = MutableLiveData<List<TvShowResponseItem>>()
    val tvShowResponse:LiveData<List<TvShowResponseItem>> get() = _tvShowResponse

    private val _searchTvShowResponse = MutableLiveData<List<SearchShowResponseItem>>()
    val searchTvShowResponse:LiveData<List<SearchShowResponseItem>> get() = _searchTvShowResponse

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

    fun searchAllTvShows(query:String) = viewModelScope.launch {

        _isLoading.postValue(true)
        myRepository.searchTvShows(query).let { response->

            if(response.isSuccessful){
                _searchTvShowResponse.postValue(response.body())
            }
            _isLoading.postValue(false)

        }


    }




}
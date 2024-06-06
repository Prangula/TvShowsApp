package com.example.tvshowsapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide.init
import com.example.tvshowsapp.model.TvShowResponseItem
import com.example.tvshowsapp.repository.MyRepository
import com.example.tvshowsapp.utils.MyDialog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MyViewModel@Inject constructor(private val myRepository: MyRepository):ViewModel() {

    private val _tvShowResponse = MutableLiveData<List<TvShowResponseItem>>()
    val tvShowResponse:LiveData<List<TvShowResponseItem>> get() = _tvShowResponse
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




}
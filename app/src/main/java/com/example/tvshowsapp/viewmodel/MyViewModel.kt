package com.example.tvshowsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tvshowsapp.model.TvShowResponseItem
import com.example.tvshowsapp.repository.MyRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MyViewModel@Inject constructor(private val myRepository: MyRepository):ViewModel() {

    private val _tvShowResponse = MutableLiveData<List<TvShowResponseItem>>()
    val tvShowResponse:LiveData<List<TvShowResponseItem>> get() = _tvShowResponse

    init {
        getAllTvShows()
    }


    private fun getAllTvShows() = viewModelScope.launch{

        myRepository.getTvShows().let { response->

            if(response.isSuccessful){
                _tvShowResponse.postValue(response.body())
            }
            else{
                response.code()
            }
        }

    }


}
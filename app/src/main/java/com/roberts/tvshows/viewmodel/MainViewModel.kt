package com.roberts.tvshows.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roberts.tvshows.data.repostory.ShowsRepository
import com.roberts.tvshows.model.Shows
import com.roberts.tvshows.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MainViewModel"
@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ShowsRepository) : ViewModel() {

    private val _shows = MutableLiveData<Resource<List<Shows>>>()
    val shows : LiveData<Resource<List<Shows>>> = _shows

    init {
        getPosts()
    }

    private fun getPosts(){
        _shows.value = Resource.Loading()
        viewModelScope.launch {
            val data = repository.getShows()
            _shows.value = data
            Log.d(TAG, "getShows: ${data.data}")
        }
    }
}
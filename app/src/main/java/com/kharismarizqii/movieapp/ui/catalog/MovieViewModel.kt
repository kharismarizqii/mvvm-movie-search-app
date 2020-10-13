package com.kharismarizqii.movieapp.ui.catalog

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.kharismarizqii.movieapp.data.MovieRepository

class MovieViewModel @ViewModelInject constructor(private val repository: MovieRepository) : ViewModel(){
    private val currentQuery = MutableLiveData(DEFAULT_QUERY)
    val movies = currentQuery.switchMap { query ->
        repository.getSearchResults(query).cachedIn(viewModelScope)
    }

    fun searchMovies(query: String){
        currentQuery.value = query
    }

    companion object{
        private const val DEFAULT_QUERY = "the"
    }
}
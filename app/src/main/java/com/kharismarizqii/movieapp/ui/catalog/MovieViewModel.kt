package com.kharismarizqii.movieapp.ui.catalog

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.kharismarizqii.movieapp.data.MovieRepository

class MovieViewModel @ViewModelInject constructor(
    private val repository: MovieRepository,
    @Assisted state: SavedStateHandle) : ViewModel(){
    private val currentQuery = state.getLiveData(CURRENT_QUERY, DEFAULT_QUERY)
    val movies = currentQuery.switchMap { query ->
        repository.getSearchResults(query).cachedIn(viewModelScope)
    }

    fun searchMovies(query: String){
        currentQuery.value = query
    }

    companion object{
        private const val CURRENT_QUERY = "current_query"
        private const val DEFAULT_QUERY = "the"
    }
}
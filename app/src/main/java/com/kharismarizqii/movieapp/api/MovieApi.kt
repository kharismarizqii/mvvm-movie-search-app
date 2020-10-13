package com.kharismarizqii.movieapp.api

import com.kharismarizqii.movieapp.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface MovieApi {

    companion object{
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val API_KEY = BuildConfig.MOVIEDB_API_KEY
    }

    @GET("search/movie?api_key=$API_KEY")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("page") page: Int
    ): MovieResponse
}
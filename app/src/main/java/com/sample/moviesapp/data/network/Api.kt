package com.sample.moviesapp.data.network

import com.sample.moviesapp.model.Movie
import com.sample.moviesapp.model.MovieResultInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
//https://api.themoviedb.org/4/list/1?api_key=481dcc539f57ba163d11b2026a71431d

    @GET("4/list/1")
    suspend fun getMovies(@Query("api_key") apiKey: String) : MovieResultInfo

}
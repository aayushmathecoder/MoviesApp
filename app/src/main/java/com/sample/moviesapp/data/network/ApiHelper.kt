package com.sample.moviesapp.data.network

class ApiHelper(private val api: Api) {
    val API_KEY = "481dcc539f57ba163d11b2026a71431d"
    suspend fun getMovies() = api.getMovies(API_KEY)
}
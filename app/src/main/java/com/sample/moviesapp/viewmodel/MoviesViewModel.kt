package com.sample.moviesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.moviesapp.model.Movie

class MoviesViewModel : ViewModel() {

   private val movieMutableLiveData : MutableLiveData<Movie> ? =null
   private val movieLiveData : LiveData<Movie> ? =null

    fun getMovies() : LiveData<Movie>? {

        return movieLiveData
    }


}
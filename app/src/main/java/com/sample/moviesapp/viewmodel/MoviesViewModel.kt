package com.sample.moviesapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.sample.moviesapp.data.repository.MoviesRepository
import com.sample.moviesapp.data.repository.getDatabase
import com.sample.moviesapp.model.Movie
import com.sample.moviesapp.utils.Callback

class MoviesViewModel(application: Application) : AndroidViewModel(application) {
    val movieLiveData: MutableLiveData<List<Movie>>? = MutableLiveData()
    private val database= getDatabase(application)
    private val moviesRepository = MoviesRepository(database)

    fun getMoviesDb()  {
         moviesRepository.getMoviesFromDb(object : Callback {
             override fun getMovies(list: List<Movie>) {
                movieLiveData?.postValue(list)
             }
         })
    }

    fun getMoviesLiveData(): MutableLiveData<List<Movie>>? {
        return movieLiveData
    }
}
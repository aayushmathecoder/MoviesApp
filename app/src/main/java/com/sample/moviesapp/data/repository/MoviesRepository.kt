package com.sample.moviesapp.data.repository

import android.util.Log
import com.sample.moviesapp.data.network.ApiHelper
import com.sample.moviesapp.data.network.RetrofitBuilder
import com.sample.moviesapp.model.Movie
import com.sample.moviesapp.utils.Callback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.lang.Exception

class MoviesRepository(private val moviesDatabase: MoviesDatabase) {
    var apiHelper: ApiHelper
    init {
        apiHelper = ApiHelper(RetrofitBuilder.apiService)
    }

    fun getMoviesFromDb(callback: Callback) {
        var list: List<Movie> = listOf()
        Log.d("starting main","")
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {
                getMoviesFromApi()
                list = moviesDatabase.moviesDao.getMovies()
            }
            //this will be executed when above finishes
            withContext(Dispatchers.Main) {
                Log.d("inside main",""+list.get(0))
                callback.getMovies(list)
            }
        }
    }

    suspend fun getMoviesFromApi()
    { /**
         * Refresh the videos stored in the offline cache.
         *
         * This function uses the IO dispatcher to ensure the database insert database operation
         * happens on the IO dispatcher. By switching to the IO dispatcher using `withContext` this
         * function is now safe to call from any thread including the Main thread.
         *
         */

           try {
               val movieslist = apiHelper.getMovies()
               Log.d("movie",movieslist.toString())
               moviesDatabase.moviesDao.insertMovies(movieslist.moviesList)
           }
           catch (e: Exception) {
               Log.d(" horrible exception", ""+e.printStackTrace())
               e.printStackTrace()
           }

    }
}
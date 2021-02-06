package com.sample.moviesapp.data.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.moviesapp.model.Movie
import com.sample.moviesapp.model.MovieResultInfo
import com.sample.moviesapp.utils.Resource


@Dao
interface MoviesDao
{
    @Query("SELECT * FROM movies")
    fun getMovies(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(moviesList:List<Movie>)
}
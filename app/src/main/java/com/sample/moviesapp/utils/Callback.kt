package com.sample.moviesapp.utils

import com.sample.moviesapp.model.Movie

interface Callback {

   fun getMovies( list: List<Movie>)
}
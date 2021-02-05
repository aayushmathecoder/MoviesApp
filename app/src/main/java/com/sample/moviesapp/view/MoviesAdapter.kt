package com.sample.moviesapp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sample.moviesapp.R
import com.sample.moviesapp.databinding.ItemMovieBinding
import com.sample.moviesapp.model.Movie

class MoviesAdapter constructor(val context: Context, val moviesList: List<Movie> ) :
    RecyclerView.Adapter<MoviesAdapter.MovieHolder>() {

    private lateinit var binding: ItemMovieBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        binding =
            DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_movie, parent, false)
        return MovieHolder(binding)
    }

    override fun getItemCount(): Int {
            return moviesList.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
            holder.bindView(moviesList.get(position))
    }

    //todo: understand
    class MovieHolder(binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(movie: Movie) {
           // binding.movieName = movi
        }

    }
}
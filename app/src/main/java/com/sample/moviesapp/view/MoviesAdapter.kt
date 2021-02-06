package com.sample.moviesapp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sample.moviesapp.R
import com.sample.moviesapp.databinding.ItemMovieBinding
import com.sample.moviesapp.model.Movie

class MoviesAdapter constructor(private val context: Context ) :
    RecyclerView.Adapter<MoviesAdapter.MovieHolder>() {

    private lateinit var binding: ItemMovieBinding
    private var moviesList: List<Movie> ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        binding =
            DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_movie, parent, false)

        return MovieHolder(binding, context)
    }

    override fun getItemCount(): Int {
            return moviesList?.size ?: 0
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
            holder.bindView(moviesList?.get(position))


    }

    fun setList(moviesList: List<Movie>?) {
        this.moviesList = moviesList
    }

    class MovieHolder(val binding: ItemMovieBinding, val context: Context) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(movie: Movie?) {
            //todo: send to xml later the entire movie, but will that be slow?
            val imgUrl = "https://image.tmdb.org/t/p/w500${movie?.posterPath}"
            Glide.with(context)
                .load(imgUrl)
                .into(binding.moviePoster);
            binding.title.text = movie?.title
            binding.rating.text = movie?.vote_average.toString()
            binding.language.text = movie?.originalLanguage?.toUpperCase()

        }

    }
}
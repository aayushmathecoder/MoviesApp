package com.sample.moviesapp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import com.bumptech.glide.Glide
import com.sample.moviesapp.R
import com.sample.moviesapp.databinding.ItemMovieBinding
import com.sample.moviesapp.model.Movie


class MoviesAdapter constructor(private val context: Context, private val onItemClickListener1: OnItemClickListener) :
    RecyclerView.Adapter<MoviesAdapter.MovieHolder>() {

    private lateinit var binding: ItemMovieBinding
    private var moviesList: SortedList<Movie> ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        binding =
            DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_movie, parent, false)

        return MovieHolder(binding, context, onItemClickListener1)
    }

    override fun getItemCount(): Int {
            return moviesList?.size() ?: 0
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
            holder.bindView(position,moviesList?.get(position))


    }

    fun sort(ascending: Boolean = true, property: String) {
        moviesList = SortedList<Movie>(Movie::class.java, object : SortedList.Callback<Movie>() {
            override fun compare(movie1: Movie, movie2: Movie): Int {
                return when {
                        property.equals("Popularity", ignoreCase = true) -> {
                            return java.lang.String.valueOf(movie1.popularity)
                                .compareTo(java.lang.String.valueOf(movie2.popularity))
                        }
                        property.equals("Rating", ignoreCase = true) -> {
                            return java.lang.String.valueOf(movie1.voteAverage)
                                .compareTo(java.lang.String.valueOf(movie2.voteAverage))
                        }
                        property.equals("Views", ignoreCase = true) -> {
                            return java.lang.String.valueOf(movie1.voteCount)
                                .compareTo(java.lang.String.valueOf(movie2.voteCount))
                        }
                        else -> movie2.title?.let { movie1.title?.compareTo(it) } ?:0
                    }
            }

            override fun onChanged(position: Int, count: Int) {
                notifyItemRangeChanged(position, count)
            }

            override fun areContentsTheSame(movie1: Movie, movie2: Movie): Boolean {
                return movie1.title.equals(movie2.title)
            }

            override fun areItemsTheSame(movie1: Movie, movie2: Movie): Boolean {
                return movie1.title.equals(movie2.title)
            }

            override fun onInserted(position: Int, count: Int) {
                notifyItemRangeInserted(position, count)
            }

            override fun onRemoved(position: Int, count: Int) {
                notifyItemRangeRemoved(position, count)
            }

            override fun onMoved(fromPosition: Int, toPosition: Int) {
                notifyItemMoved(fromPosition, toPosition)
            }
        })
    }

    fun addAll(starList: List<Movie?>) {
        moviesList?.beginBatchedUpdates()
        for (i in starList.indices) {
            moviesList?.add(starList[i])
        }
        moviesList?.endBatchedUpdates()
    }

    operator fun get(position: Int): Movie? {
        return moviesList?.get(position)
    }

    fun clear() {
        moviesList?.beginBatchedUpdates()
        //remove items at end, to avoid unnecessary array shifting
        while (moviesList?.size()!! > 0) {
            moviesList?.size()?.minus(1)?.let { moviesList?.removeItemAt(it) }
        }
        moviesList?.endBatchedUpdates()
    }

    interface OnItemClickListener {
        fun onItemClicked(movie: Movie?)
    }

    class MovieHolder(
        private val binding: ItemMovieBinding,
        val context: Context,
        private val onItemClickListener1: OnItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(position: Int, movie: Movie?) {
            val imgUrl = "https://image.tmdb.org/t/p/w500${movie?.posterPath}"
            Glide.with(context)
                .load(imgUrl)
                .into(binding.moviePoster)
            binding.title.text = movie?.title
            binding.rating.text = movie?.voteAverage.toString()
            binding.language.text = movie?.originalLanguage?.toUpperCase()


            binding.root.setOnClickListener {
                onItemClickListener1.onItemClicked(movie)
            }
        }

    }
}
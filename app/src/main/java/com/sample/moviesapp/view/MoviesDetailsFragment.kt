package com.sample.moviesapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.sample.moviesapp.R
import com.sample.moviesapp.databinding.FragmentMoviesDetailsBinding
import com.sample.moviesapp.model.Movie
import com.sample.moviesapp.viewmodel.MoviesViewModel
import com.sample.moviesapp.viewmodel.ViewModelFactory

class MoviesDetailsFragment : Fragment() {

    private var binding: FragmentMoviesDetailsBinding? = null
    private lateinit var moviesViewModel: MoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.fragment_movies_details,
            null,
            false
        )

        return binding!!.root
    }

    private fun initUI() {
        if(binding == null)
            return

        if(arguments != null) {
            val movie = arguments!!.getParcelable<Movie>(KEY_MOVIE)
            binding!!.title.text = movie?.title
            binding!!.rating.text = movie?.voteAverage.toString()
            binding!!.language.text = movie?.originalLanguage?.toUpperCase()
            binding!!.description.text = movie?.overview
            val imgUrl = "https://image.tmdb.org/t/p/w500${movie?.posterPath}"
            context?.let {
                Glide.with(it)
                    .load(imgUrl)
                    .into(binding!!.moviePoster)
            }
        }
    }

    private fun initViewModel() {
        moviesViewModel = ViewModelProvider(
            activity as MainActivity,
            ViewModelFactory(activity!!.application)
        ).get(MoviesViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initUI()
    }

    companion object {
        const val KEY_POSITION_CLICKED = "position_clicked"
        const val KEY_MOVIE = "movie"
        fun newInstance(movie: Movie): MoviesDetailsFragment {
            val fragment = MoviesDetailsFragment()
            val bundle = Bundle()
            bundle.putParcelable(KEY_MOVIE, movie)
            fragment.arguments = bundle
            return fragment
        }
    }
}
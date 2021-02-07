package com.sample.moviesapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.moviesapp.R
import com.sample.moviesapp.databinding.FragmentMoviesListBinding
import com.sample.moviesapp.model.Movie
import com.sample.moviesapp.viewmodel.MoviesViewModel
import com.sample.moviesapp.viewmodel.ViewModelFactory


class MoviesListFragment : Fragment(), MoviesAdapter.OnItemClickListener {

    private var moviesAdapter: MoviesAdapter? = null
    private var binding: FragmentMoviesListBinding? = null
    private lateinit var moviesViewModel: MoviesViewModel
    private var moviesList: List<Movie> ? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.fragment_movies_list,
            null,
            false
        )

        return binding!!.root
    }

    private fun initUI() {
        moviesAdapter = context?.let { MoviesAdapter(it, this) }
        binding?.moviesListRecyclerview?.layoutManager = LinearLayoutManager(context)
        binding?.moviesListRecyclerview?.adapter = moviesAdapter

        moviesViewModel.movieLiveData?.observe(activity as MainActivity, Observer {
            moviesAdapter?.addAll(it)
            moviesList = it
            moviesAdapter?.notifyDataSetChanged()
        })
    }

    private fun fetchData() {
        moviesViewModel.getMoviesDb()
    }

    private fun initViewModel() {
        moviesViewModel = ViewModelProvider(
            this,
            ViewModelFactory(activity!!.application)
        ).get(MoviesViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initUI()
        fetchData()
        prepareSpinner()
    }

    private fun prepareSpinner() {
        val properties = arrayOf("Popularity", "Rating", "Name", "Views")

        binding?.propertySpinner?.adapter = context?.let {
            ArrayAdapter(
                it, android.R.layout.simple_dropdown_item_1line,
                properties
            )
        }

        binding?.propertySpinner?.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val SELECTED_PROPERTY = properties[position]
                moviesAdapter?.sort(true, SELECTED_PROPERTY)
                moviesList?.let { moviesAdapter?.addAll(it) }
                moviesAdapter?.notifyDataSetChanged()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    companion object {
        fun newInstance(): MoviesListFragment {
            return MoviesListFragment()
        }
    }

    override fun onItemClicked( movie: Movie?) {
        val fragment = movie?.let { MoviesDetailsFragment.newInstance( it) }
        if (fragment != null) {
            activity!!.supportFragmentManager.
            beginTransaction().
            replace(R.id.container, fragment).
            addToBackStack(null).
            commit()
        }
    }
}
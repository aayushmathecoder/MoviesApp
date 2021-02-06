package com.sample.moviesapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.moviesapp.R
import com.sample.moviesapp.data.network.ApiHelper
import com.sample.moviesapp.data.network.RetrofitBuilder
import com.sample.moviesapp.databinding.FragmentMoviesListBinding
import com.sample.moviesapp.utils.Status
import com.sample.moviesapp.viewmodel.MoviesViewModel
import com.sample.moviesapp.viewmodel.ViewModelFactory

class MoviesListFragment : Fragment() {

    private var moviesAdapter: MoviesAdapter? = null
    private var binding: FragmentMoviesListBinding? = null
    private lateinit var moviesViewModel: MoviesViewModel

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
        moviesAdapter = context?.let { MoviesAdapter(it) }
        binding?.moviesListRecyclerview?.layoutManager = LinearLayoutManager(context)
        binding?.moviesListRecyclerview?.adapter = moviesAdapter
        moviesViewModel.movieLiveData?.observe(activity as MainActivity, Observer {
            moviesAdapter?.setList(it)
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
    }

    companion object {
        fun newInstance(): MoviesListFragment {
            return MoviesListFragment()
        }
    }

}
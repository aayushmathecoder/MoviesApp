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
import com.sample.moviesapp.databinding.FragmentMoviesListBinding
import com.sample.moviesapp.viewmodel.MoviesViewModel

class MoviesListFragment : Fragment(){

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

        initViewModel()
        initUI()
        fetchData()
        return binding!!.root
    }

    private fun initUI() {
        binding?.moviesListRecyclerview?.layoutManager  = LinearLayoutManager(context)
        //todo:
       // binding?.moviesListRecyclerview?.adapter =

    }

    private fun fetchData() {
        moviesViewModel.getMovies()?.observe(viewLifecycleOwner, Observer {

            //adapter.notifyDataChanged


        })
    }

    private fun initViewModel() {
        moviesViewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
    }

}
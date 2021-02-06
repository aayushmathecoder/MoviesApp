package com.sample.moviesapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.moviesapp.data.repository.MoviesRepository
import com.sample.moviesapp.data.repository.getDatabase

class ViewModelFactory constructor(val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) {
            return MoviesViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}


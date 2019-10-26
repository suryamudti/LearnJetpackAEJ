package com.surya.mvvm.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.surya.mvvm.data.entity.Movies
import com.surya.mvvm.data.repository.MovieRepository
import com.surya.mvvm.network.NetworkListener

class MainViewModel(private val repository: MovieRepository) : ViewModel() {
    var networkListener: NetworkListener? = null
    fun getMovies(): LiveData<List<Movies>> = repository._result
    fun fetchData() = repository.getMovies(networkListener)
}
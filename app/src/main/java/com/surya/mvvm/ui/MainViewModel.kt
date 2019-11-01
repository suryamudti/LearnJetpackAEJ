package com.surya.mvvm.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.surya.mvvm.data.entity.Movies
import com.surya.mvvm.data.repository.MovieRepository
import com.surya.mvvm.network.NetworkListener

class MainViewModel(private val repository: MovieRepository) : ViewModel() {

    /**
    * interface for callback
    * */
    var networkListener: NetworkListener? = null

    /**
     * stream the result from repository
     * */
    fun getMovies(): LiveData<List<Movies>> = repository._result

    /**
     * fetch movies data
     * */
    fun fetchData() = repository.getMovies(networkListener)
}
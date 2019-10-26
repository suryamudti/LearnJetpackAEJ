package com.surya.mvvm.data.repository

import androidx.lifecycle.MutableLiveData
import com.surya.mvvm.data.entity.Movies
import com.surya.mvvm.data.entity.ResponseMovies
import com.surya.mvvm.network.NetworkListener
import com.surya.mvvm.network.Routes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository(private val routes: Routes) {

    /*
    * This is for initialize the data
    * setValue() for main thread
    * postValue() for asynchronous
    * */
    val _result = MutableLiveData<List<Movies>>()

    /*
    * fetch the data from TMDB API using Retrofit
    * */
    fun getMovies(networkListener: NetworkListener?) {
        routes.getMovies().enqueue(object : Callback<ResponseMovies> {
            override fun onResponse(call: Call<ResponseMovies>, response: Response<ResponseMovies>) {
                if (response.isSuccessful) {
                    networkListener?.onSuccess(response.body()?.results.toString())
                    _result.value = response.body()?.results
                } else{
                    networkListener?.onFailure(response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<ResponseMovies>, t: Throwable) {
                networkListener?.onFailure(t.message)
            }
        })
    }

}
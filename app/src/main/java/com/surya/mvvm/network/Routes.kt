package com.surya.mvvm.network

import com.surya.mvvm.data.entity.ResponseMovies
import retrofit2.Call
import retrofit2.http.GET

interface Routes {

    /*
    * end_point base url
    * */
    @GET("discover/movie")
    fun getMovies() : Call<ResponseMovies>

}
package com.surya.mvvm.network

interface NetworkListener {
    fun onSuccess(msg: String)
    fun onFailure(msg: String?)
}
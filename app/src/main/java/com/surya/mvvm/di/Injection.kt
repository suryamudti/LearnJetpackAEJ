package com.surya.mvvm.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.surya.mvvm.data.repository.MovieRepository
import com.surya.mvvm.network.Network
import com.surya.mvvm.ui.ViewModelFactory

object Injection {
    fun provideRepository(): MovieRepository {
        return MovieRepository(Network.routes())
    }

    fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
        return ViewModelFactory(provideRepository())
    }
}
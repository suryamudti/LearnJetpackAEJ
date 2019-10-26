package com.surya.mvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.surya.mvvm.R
import com.surya.mvvm.data.entity.Movies
import com.surya.mvvm.di.Injection
import com.surya.mvvm.network.NetworkListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.shimmer_layout.*

class MainActivity : AppCompatActivity(), NetworkListener {

    private val data = arrayListOf<Movies>()

    private val adapter: MainAdapter by lazy {
        MainAdapter(data)
    }

    // Declare the ViewModel
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the View Model
        viewModel =
            ViewModelProviders
                .of(this, Injection.provideViewModelFactory(this))
                .get(MainViewModel::class.java)

        swipe_to_refresh_layout.setOnRefreshListener {
            fetchData()
        }

        // Start observing the Live Data
        initObserver()

        // Do request to API
        fetchData()
    }

    private fun initObserver() {
        viewModel.networkListener = this
        viewModel.getMovies().observe(this, Observer { listMovies ->
            showDataMovie(listMovies)
        })
    }

    /*
    * showing the Data
    * @listMovies from the observed liveData
    * */
    private fun showDataMovie(listMovies: List<Movies>) {
        rv.setHasFixedSize(true)
        rv.layoutManager = GridLayoutManager(this,2)
        adapter.updateData(listMovies)
        rv.adapter = adapter
    }

    /*
    * fetching data from Api by using viewModel
    * */
    private fun fetchData() {
        startShimmer()
        viewModel.fetchData()
    }

    override fun onSuccess(data: String) {
        Log.d("TAG", data)
        stopShimmer()
        swipe_to_refresh_layout.isRefreshing = false
    }

    override fun onFailure(msg: String?) {
        Log.e("TAG", msg!!)
        stopShimmer()
        swipe_to_refresh_layout.isRefreshing = false
    }

    /*
    * start the shimmer loading
    * */
    private fun startShimmer() {
        rv.visibility = View.GONE
        shimmering_layout.visibility = View.VISIBLE
        shimmering_layout.startShimmer()
    }

    /*
    * stop the shimmer loading
    * */
    private fun stopShimmer() {
        rv.visibility = View.VISIBLE
        shimmering_layout.stopShimmer()
        shimmering_layout.visibility = View.GONE
    }

    /*
    * handle on Pause to stop the Shimmer
    * */
    override fun onPause() {
        super.onPause()
        stopShimmer()
    }
}

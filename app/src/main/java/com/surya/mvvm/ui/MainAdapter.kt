package com.surya.mvvm.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.surya.mvvm.BuildConfig
import com.surya.mvvm.R
import com.surya.mvvm.data.entity.Movies
import com.surya.mvvm.displayImageOriginal
import com.surya.mvvm.inflate
import kotlinx.android.synthetic.main.item_movie.view.*

class MainAdapter(private val list: ArrayList<Movies>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent.inflate(R.layout.item_movie))

    fun updateData(dataMovies: List<Movies>) {
        this.list.clear()
        this.list.addAll(dataMovies)
        notifyDataSetChanged()
    }

    @Suppress("USELESS_ELVIS")
    override fun getItemCount(): Int = list.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var movies: Movies? = null

        fun bindItem(data: Movies?) {
            this.movies = data
            itemView.run {
                tv_title.text = data?.title
                tv_popularity.text = data?.popularity.toString()
                tv_release.text = data?.releaseDate
                displayImageOriginal(context!!, iv_poster, BuildConfig.IMAGES + data?.posterPath)
            }
        }
    }
}
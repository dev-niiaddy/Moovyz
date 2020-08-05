package com.orbilax.moovyz.custom

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.orbilax.moovyz.R
import com.orbilax.moovyz.model.MovieItem
import com.orbilax.moovyz.extentsions.inflateLayout
import kotlinx.android.synthetic.main.staggered_movie_item.view.*
import kotlin.random.Random

class StaggeredPagingAdapter :
    PagingDataAdapter<MovieItem, MovieItemViewHolder>(
        MOVIE_ITEM_COMPARATOR
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val view = parent
            .inflateLayout(R.layout.staggered_movie_item)


        view.posterCardView.layoutParams?.apply {
            height = Random.nextInt(500, 701)
        }

        return MovieItemViewHolder(view, shouldAnimate = false)
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        val movieItem = getItem(position)
        holder.bindMovieItem(movieItem)
    }

    companion object {

        private val MOVIE_ITEM_COMPARATOR = object : DiffUtil.ItemCallback<MovieItem>() {
            override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
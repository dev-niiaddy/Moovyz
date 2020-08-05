package com.orbilax.moovyz.custom

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.orbilax.moovyz.R
import com.orbilax.moovyz.model.MovieItem
import com.orbilax.moovyz.extentsions.inflateLayout
import kotlinx.android.synthetic.main.card_movie_item.view.*

class PagingMovieItemAdapter(private val isLargeCard: Boolean = false) :
    PagingDataAdapter<MovieItem, MovieItemViewHolder>(
        MOVIE_ITEM_COMPARATOR
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val view = parent
            .inflateLayout(R.layout.card_movie_item)

        if(!isLargeCard) {
            view.materialCardView.layoutParams.apply {
                height =  350
                width = 250
            }
        }

        return MovieItemViewHolder(view)
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
package com.orbilax.moovyz.remove.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.orbilax.moovyz.R
import com.orbilax.moovyz.model.MovieItem
import com.orbilax.moovyz.api.TMDBService
import com.orbilax.moovyz.util.loadNetworkImage
import kotlinx.android.synthetic.main.card_fixed_movie_item.view.*

class MovieItemAdapter :
    PagingDataAdapter<MovieItem, MovieItemAdapter.MovieItemViewHolder>(MOVIE_ITEM_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.card_fixed_movie_item, parent, false)

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

    class MovieItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bindMovieItem(movieItem: MovieItem?) {
            if(movieItem != null) {
                movieItem.posterPath?.let {
                    view.moviePosterImageView
                        .loadNetworkImage(TMDBService.tmdbImageUrl(it))
                }

                view.movieTitle.text = movieItem.title
                animateCard()
            }
        }

        private fun animateCard() {
            view.translationX = 200f
            val springAnim = SpringAnimation(view, SpringAnimation.TRANSLATION_X, 0f).apply {
                spring.dampingRatio = SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY
                spring.stiffness = SpringForce.STIFFNESS_LOW
            }
            springAnim.animateToFinalPosition(0f)
        }
    }
}
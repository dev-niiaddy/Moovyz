package com.orbilax.moovyz.adapters

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
import com.orbilax.moovyz.service.TMDBService
import com.orbilax.moovyz.util.loadImage
import kotlinx.android.synthetic.main.card_fixed_movie_item.view.*
import kotlin.random.Random

class StaggeredMovieItemAdapter :
    PagingDataAdapter<MovieItem, StaggeredMovieItemAdapter.StaggeredMovieViewHolder>(
        MOVIE_ITEM_COMPARATOR
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StaggeredMovieViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.card_staggered_movie_item, parent, false)

        view.layoutParams.height = Random.nextInt(300, 501)

        return StaggeredMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: StaggeredMovieViewHolder, position: Int) {
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

    class StaggeredMovieViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {


        fun bindMovieItem(movieItem: MovieItem?) {
            if (movieItem != null) {
                movieItem.posterPath?.let {
                    view.moviePosterImageView
                        .loadImage(TMDBService.tmdbImageUrl(it))
                }

                view.movieTitle.text = movieItem.title
            }
        }
    }
}
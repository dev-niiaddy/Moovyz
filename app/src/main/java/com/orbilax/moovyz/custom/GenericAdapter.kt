package com.orbilax.moovyz.custom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.orbilax.moovyz.R

class GenericAdapter<T>(
    private val items: List<T>,
    @LayoutRes private val viewRes: Int = R.layout.card_fixed_movie_item
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(viewRes, parent, false)
        return MovieItemViewHolder(view)

//        return when {
//            items.all { it is MovieItem } -> {
//                val view = LayoutInflater.from(parent.context)
//                    .inflate(R.layout.card_fixed_movie_item, parent, false)
//                MovieItemViewHolder(view)
//            }
//            else -> {
//                val view = LayoutInflater.from(parent.context)
//                    .inflate(R.layout.card_fixed_loading_movie_item, parent, false)
//                LoadingViewHolder(view)
//            }
//        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is Binder<*>) {
            (holder as Binder<T>).bindItem(items[position])
        }
    }

    internal interface Binder<T> {
        fun bindItem(item: T)
    }
}
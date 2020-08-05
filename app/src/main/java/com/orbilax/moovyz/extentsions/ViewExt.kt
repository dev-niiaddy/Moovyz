package com.orbilax.moovyz.extentsions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import com.bumptech.glide.Glide

// MARK: - ImageView
fun ImageView.loadNetworkImage(url: String) {
    Glide.with(this.context)
        .load(url)
        .into(this)
}

// MARK: - ViewGroup
fun ViewGroup.inflateLayout(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(this.context).inflate(layoutRes, this, attachToRoot)
}
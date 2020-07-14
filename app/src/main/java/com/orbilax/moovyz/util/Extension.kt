package com.orbilax.moovyz.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

//val <T> T.exhaustive: T
//    get() = this

// MARK: - ImageView
fun ImageView.loadNetworkImage(url: String) {
    Glide.with(this.context)
        .load(url)
        .into(this)
}

// MARK: - String

// MARK: - Gson
inline fun <reified T> Gson.fromJson(json: String): T {
    return Gson().fromJson(json, object : TypeToken<T>(){}.type)
}
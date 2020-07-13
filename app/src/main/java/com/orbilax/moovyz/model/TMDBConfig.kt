package com.orbilax.moovyz.model

import com.google.gson.annotations.SerializedName

data class TMDBConfig(
    @SerializedName("images")
    val imagesConfig: ImagesConfig,
    @SerializedName("change_keys")
    val changeKeys: List<String>?
)


data class ImagesConfig(
    @SerializedName("poster_sizes")
    val posterSizes: List<String>?,
    @SerializedName("secure_base_url")
    val secureBaseUrl: String = "",
    @SerializedName("backdrop_sizes")
    val backdropSizes: List<String>?,
    @SerializedName("base_url")
    val baseUrl: String = "",
    @SerializedName("logo_sizes")
    val logoSizes: List<String>?,
    @SerializedName("still_sizes")
    val stillSizes: List<String>?,
    @SerializedName("profile_sizes")
    val profileSizes: List<String>?
)
package com.orbilax.moovyz.model

import com.google.gson.annotations.SerializedName

data class PageResult<T>(
    @SerializedName("dates")
    val dates: Dates,
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("total_results")
    val totalResults: Int = 0,
    @SerializedName("results")
    val results: List<T> = emptyList()
)

data class Dates(
    @SerializedName("maximum")
    val maximum: String = "",
    @SerializedName("minimum")
    val minimum: String = ""
)

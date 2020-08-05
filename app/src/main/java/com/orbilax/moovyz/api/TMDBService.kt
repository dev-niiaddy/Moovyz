package com.orbilax.moovyz.api

import com.orbilax.moovyz.model.TMDBConfig
import com.orbilax.moovyz.util.MoviePage
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface TMDBService {

    @GET("configuration")
    suspend fun getConfiguration(): TMDBConfig

    @GET("movie/upcoming")
    suspend fun getComingSoon(
        @Query("page") pageNo: Int
    ): MoviePage

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("page") pageNo: Int
    ): MoviePage

    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("page") pageNo: Int
    ): MoviePage

    @GET("movie/popular")
    suspend fun getPopular(
        @Query("page") pageNo: Int
    ): MoviePage

    @GET("discover/movie")
    suspend fun getExploreMovie(
        @Query("page") pageNo: Int
    ): MoviePage


    companion object {
        private const val TIME_OUT_IN_SECONDS = 60L

        private const val API_KEY = "ab0d2a1c784226043ce16ce959090bf7"
        private const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"

        private val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)

        operator fun invoke(): TMDBService {
            val httpClient = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val initialRequest = chain.request()
                    val initialUrl = initialRequest.url()

                    val newUrl = initialUrl.newBuilder()
                        .addQueryParameter("api_key",
                            API_KEY
                        )
                        .addQueryParameter("language", "en-US")
                        .build()

                    val newRequest = initialRequest.newBuilder()
                        .url(newUrl).build()

                    return@addInterceptor chain.proceed(newRequest)
                }
                .readTimeout(TIME_OUT_IN_SECONDS, TimeUnit.SECONDS)
                .connectTimeout(TIME_OUT_IN_SECONDS, TimeUnit.SECONDS)
                .build()

            return retrofitBuilder
                .client(httpClient).build()
                .create(TMDBService::class.java)
        }

        fun tmdbImageUrl(path: String, quality:String = "w500"): String {
//            Log.i("Poster Path", "$path")
            return "$IMAGE_BASE_URL$quality/$path"
        }
    }
}
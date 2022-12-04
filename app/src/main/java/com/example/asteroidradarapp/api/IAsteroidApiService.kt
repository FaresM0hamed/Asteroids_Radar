package com.example.asteroidradarapp


import retrofit2.http.GET
import retrofit2.http.Query

interface IAsteroidApiService {

    @GET(NetworkConstants.HTTP_GET_NEO_FEED_PATH)
    suspend fun getAsteroids(
        @Query(NetworkConstants.QUERY_API_KEY_PARAM) apiKey: String): String

    @GET(NetworkConstants.HTTP_GET_APOD_PATH)
    suspend fun getPictureOfDay(@Query(NetworkConstants.QUERY_API_KEY_PARAM) apiKey: String) : PictureOfDay
}
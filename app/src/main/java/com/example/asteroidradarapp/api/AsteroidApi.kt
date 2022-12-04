package com.example.asteroidradarapp.api

import com.example.asteroidradarapp.IAsteroidApiService
import com.example.asteroidradarapp.NetworkConstants
import com.example.asteroidradarapp.database.AsteroidModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object AsteroidApi {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private fun getClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(getClient())
        .baseUrl(NetworkConstants.BASE_URL)
        .build()

    private val retrofitService: IAsteroidApiService by lazy {
        retrofit.create(IAsteroidApiService::class.java)
    }

    suspend fun getRemoteAsteroids(): ArrayList<AsteroidModel> {
        val responseStr = retrofitService.getAsteroids(NetworkConstants.API_KEY)
        val responseJsonObject = JSONObject(responseStr)

        return parseAsteroidsJsonResult(responseJsonObject)
    }

    suspend fun getPictureOfDay() = retrofitService.getPictureOfDay(NetworkConstants.API_KEY)
}
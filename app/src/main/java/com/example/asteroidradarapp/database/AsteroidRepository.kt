package com.example.asteroidradarapp.database

import com.example.asteroidradarapp.PictureOfDay
import com.example.asteroidradarapp.api.AsteroidApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AsteroidsRepository(private val database: AsteroidDatabase) {


    suspend fun refreshAsteroids(): List<AsteroidModel> {
        val asteroids = AsteroidApi.getRemoteAsteroids()
        withContext(Dispatchers.IO) {
            database.dao.insertAll(asteroids)
        }
        return asteroids.toList()
    }

    suspend fun getSavedAsteroids(): List<AsteroidModel> {
        val asteroids = AsteroidApi.getRemoteAsteroids()
        withContext(Dispatchers.IO) {
            database.dao.getAll()
        }
        return asteroids.toList()
    }

    suspend fun getPictureOfDay(): PictureOfDay {
        lateinit var pictureOfDay: PictureOfDay
        withContext(Dispatchers.IO) {
            pictureOfDay = AsteroidApi.getPictureOfDay()
        }
        return pictureOfDay
    }


}
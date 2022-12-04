package com.example.asteroidradarapp.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.asteroidradarapp.Asteroid
import com.example.asteroidradarapp.PictureOfDay
import com.example.asteroidradarapp.api.AsteroidApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AsteroidsRepository(private val database: AsteroidDatabase) {

    val asteroids: LiveData<List<Asteroid>> =
        Transformations.map(database.dao.getAll()) {
            it.asAsteroids()
        }

    suspend fun refreshAsteroids() {
        val asteroids = AsteroidApi.getRemoteAsteroids()
        withContext(Dispatchers.IO) {
            database.dao.insertAll(asteroids)
        }
    }

    suspend fun getPictureOfDay(): PictureOfDay {
        lateinit var pictureOfDay: PictureOfDay
        withContext(Dispatchers.IO) {
            pictureOfDay = AsteroidApi.getPictureOfDay()
        }
        return pictureOfDay
    }
}
package com.example.asteroidradarapp.application

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.asteroidradarapp.database.AsteroidDatabase
import com.example.asteroidradarapp.database.AsteroidsRepository
import retrofit2.HttpException

/**
 * workerClass to make long-running task : getting asteroids
 * and cache it when device is connected to network and charging
 */
class GetAsteroidWorker(appContext: Context, params: WorkerParameters):
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "RefreshAsteroidsWorker"
    }

    override suspend fun doWork(): Result {

        val database = AsteroidDatabase.getInstance(applicationContext)
        val repository = AsteroidsRepository(database)

        return try {
            repository.refreshAsteroids()
            Result.success()

        } catch (e: HttpException) {
            Result.retry()
        }
    }
}
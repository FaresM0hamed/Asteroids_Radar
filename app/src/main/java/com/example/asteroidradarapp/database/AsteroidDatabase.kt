package com.example.asteroidradarapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.asteroidradarapp.DatabaseConstants

@Database(version = 1,entities = [AsteroidModel::class])
abstract class AsteroidDatabase : RoomDatabase() {

    abstract val dao: IAsteroidDao

    companion object {
        @Volatile
        private lateinit var instance: AsteroidDatabase

        fun getInstance(context: Context): AsteroidDatabase {
            synchronized(AsteroidDatabase::class.java) {
                if(!::instance.isInitialized) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AsteroidDatabase::class.java,
                        DatabaseConstants.DATABASE_FILE_NAME)
                        .build()
                }
            }
            return instance
        }
    }
}
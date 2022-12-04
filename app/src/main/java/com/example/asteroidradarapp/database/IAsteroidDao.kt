package com.example.asteroidradarapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.asteroidradarapp.DatabaseConstants

@Dao
interface IAsteroidDao {

    @Query("SELECT * FROM ${DatabaseConstants.TABLE_NAME} ORDER by closeApproachDate")
    fun getAll(): LiveData<List<AsteroidModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(asteroids: List<AsteroidModel>)
}
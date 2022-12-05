package com.example.asteroidradarapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.asteroidradarapp.DatabaseConstants
import java.io.Serializable


@Entity(tableName = DatabaseConstants.TABLE_NAME)
data class AsteroidModel(
    @PrimaryKey
    val id: Long,
    val codename: String,
    val closeApproachDate: String,
    val absoluteMagnitude: Double,
    val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    val isPotentiallyHazardous: Boolean) : Serializable
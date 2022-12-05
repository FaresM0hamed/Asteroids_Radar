package com.example.asteroidradarapp

import com.example.asteroidradarapp.database.AsteroidModel

class AsteroidItemClickListener(val clickListener: (AsteroidModel) -> Unit) {
    fun onClick(data: AsteroidModel) = clickListener(data)
}
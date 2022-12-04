package com.example.asteroidradarapp

class AsteroidItemClickListener(val clickListener: (Asteroid) -> Unit) {
    fun onClick(data: Asteroid) = clickListener(data)
}
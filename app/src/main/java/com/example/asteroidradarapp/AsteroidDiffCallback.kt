package com.example.asteroidradarapp

import androidx.recyclerview.widget.DiffUtil
import com.example.asteroidradarapp.database.AsteroidModel

class AsteroidDiffCallback: DiffUtil.ItemCallback<AsteroidModel>() {
    override fun areItemsTheSame(oldItem: AsteroidModel, newItem: AsteroidModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AsteroidModel, newItem: AsteroidModel): Boolean {
        return oldItem == newItem
    }
}

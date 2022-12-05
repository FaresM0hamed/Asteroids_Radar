package com.example.asteroidradarapp

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.asteroidradarapp.database.AsteroidModel

class AsteroidListAdapter(private val itemClickListener: AsteroidItemClickListener)
    : ListAdapter<AsteroidModel, AsteroidViewHolder>(AsteroidDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {
        return AsteroidViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, itemClickListener)
    }
}
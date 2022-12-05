package com.example.asteroidradarapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.asteroidradarapp.database.AsteroidDatabase
import com.example.asteroidradarapp.database.AsteroidModel
import com.example.asteroidradarapp.database.AsteroidsRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

/**
 * This class used to save state , and it lifecycle aware
 */
class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val database = AsteroidDatabase.getInstance(app)
    private val repository = AsteroidsRepository(database)

    private val _pictureOfDay = MutableLiveData<PictureOfDay>()
    val pictureOfDay: LiveData<PictureOfDay>
        get() = _pictureOfDay

    private val _navigateToDetailFragment = MutableLiveData<AsteroidModel?>()
    val navigateToDetailFragment
        get() = _navigateToDetailFragment

    private val _asteroids = MutableLiveData<List<AsteroidModel>>()
    val asteroids: LiveData<List<AsteroidModel>> get() = _asteroids

    private val _todayAsteroids = MutableLiveData<List<AsteroidModel>>()

    init {
        refreshAsteroids()
        getPictureOfDay()
    }

    fun onAsteroidItemClick(data: AsteroidModel) {
        _navigateToDetailFragment.value = data
    }

    fun onDetailFragmentNavigated() {
        _navigateToDetailFragment.value = null
    }

    private fun refreshAsteroids() {
        viewModelScope.launch {
            try {
                _asteroids.postValue(repository.refreshAsteroids())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun getPictureOfDay() {
        viewModelScope.launch {
            try {
                _pictureOfDay.value = repository.getPictureOfDay()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    private fun clearAsteroids() {
        _asteroids.postValue(listOf())
    }

    fun filterWeekAsteroids() {
        clearAsteroids()
        refreshAsteroids()
    }

    fun filterTodayAsteroids() {
        clearAsteroids()
        viewModelScope.launch{
            val allAsteroids = repository.refreshAsteroids()
            val filteredAsteroids = mutableListOf<AsteroidModel>()
            for(asteroid in allAsteroids){
                if(asteroid.closeApproachDate==getCurrentDate()){
                    filteredAsteroids.add(asteroid)
                }
            }
            _asteroids.postValue(filteredAsteroids)
        }
    }

    fun filterSavedAsteroids() {
        clearAsteroids()
        viewModelScope.launch{
            val allAsteroids = repository.getSavedAsteroids()
            _asteroids.postValue(allAsteroids)
        }
    }

    private fun getCurrentDate(): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = Date()
        return formatter.format(date)
    }


}
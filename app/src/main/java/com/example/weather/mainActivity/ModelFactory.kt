package com.example.weather.mainActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ModelFactory(private var latitude: Double, private var longitude: Double) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T?>): T {
        return if (modelClass == MainViewModel::class.java) {
            MainViewModel(latitude, longitude) as T
        } else throw IllegalArgumentException("Invalid view model type")
    }

}

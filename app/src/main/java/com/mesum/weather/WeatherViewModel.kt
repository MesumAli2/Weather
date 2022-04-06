package com.mesum.weather

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mesum.weather.model.WeatherModel
import kotlinx.coroutines.launch
import java.lang.Exception

class WeatherViewModel : ViewModel() {
    private val _WeatherData = MutableLiveData<WeatherModel>()
    val watherData  : LiveData<WeatherModel> get() = _WeatherData
    init {
        getWeather("dubai")
    }
    fun getWeather(location : String) {
        viewModelScope.launch {

            try {
              _WeatherData.value =  ApiClient.apiService.fetchCharacters("64816698aa8fa31e4c3fb603747d54e1", "New York")

            }catch (e : Exception){
                Log.d("WeatherViewModel", e.message.toString())
                Log.d("fail", "no working man")


            }

        }
    }
}
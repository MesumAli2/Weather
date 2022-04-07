package com.mesum.weather

import android.R
import android.content.Context
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mesum.weather.model.WeatherModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class WeatherViewModel : ViewModel() {
    private val _WeatherData = MutableLiveData<WeatherModel>()
    val watherData  : LiveData<WeatherModel> get() = _WeatherData
    var city  = "fetch:ip"

    init {
        getWeather(city)
    }
    fun getWeather(location : String) {
        viewModelScope.launch {
            try {
               // _WeatherData.value?.set(0, WeatherApi.weatherRequest.getCurrentWeather(location))
                _WeatherData.value = WeatherApi.weatherRequest.getCurrentWeather(location)

            }catch (e : Exception){
                Log.d("WeatherViewModel", e.message.toString())
                Log.d("fail", "no working man")

            }
        }
    }


     /*fun getWeatherInfo(location: String){
        val call = WeatherApi.weatherRequest.getCurrentWeather(location)
        call.enqueue(object : Callback<WeatherModel>{
            override fun onResponse(call: Call<WeatherModel>, response: Response<WeatherModel>) {
                _WeatherData?.value?.get(0) ?: response.body()!!
            }

            override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                    Log.d("fail", t.message.toString())
            }
        })

    }*/






}
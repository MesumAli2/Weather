package com.mesum.weather

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mesum.weather.model.WeatherModel

import kotlinx.coroutines.launch

class ApiViewModel: ViewModel() {

    private val _result = MutableLiveData<WeatherModel>()
    val result:LiveData<WeatherModel> = _result

     var city  = "fetch:ip"


    init {
        getChracters()
    }
     fun getChracters(){

        viewModelScope.launch {
            try {
                _result.value = ApiClient.apiService.fetchCharacters("64816698aa8fa31e4c3fb603747d54e1", "$city")
                Log.d("response", "${_result.value}")
                //result.value?.let { datalist.add(it) }
            }catch (e: Exception){

                Log.d("fail", "no working man")
            }
        }
    }


}
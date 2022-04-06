package com.mesum.weather

import com.mesum.weather.model.WeatherModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//private val BASE_URL = "https://rickandmortyapi.com/api/"
private val BASeurl = "http://api.weatherstack.com/"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit: Retrofit by lazy {
    Retrofit.Builder()
        .baseUrl(BASeurl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
}


interface ApiService{

    @GET("current")

   suspend fun fetchCharacters(@Query("access_key") accesKey: String = "1be7e03654713a586a3b62aea2097e32", @Query("query") location:String): WeatherModel
}


object ApiClient {

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

}
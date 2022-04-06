package com.mesum.weather

import com.mesum.weather.model.WeatherModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "1be7e03654713a586a3b62aea2097e32"
private const val BASE_URL = "http://api.weatherstack.com"

private val requestInterceptor = Interceptor{
        chain ->
    val url = chain.request()
        .url()
        .newBuilder()
        .addQueryParameter("access_key", API_KEY)
        .build()
    val request = chain.request()
        .newBuilder()
        .url(url)
        .build()
    return@Interceptor chain.proceed(request)
}

val okHttpClient = OkHttpClient.Builder()

    .addInterceptor(requestInterceptor)
    .build()



private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit: Retrofit by lazy {
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
}


interface WeatherApiservice{
    @GET("current")
    suspend fun getCurrentWeather(@Query("access_key")  accessKey: String = API_KEY, @Query("query") location:String): WeatherModel
}

object WeatherApi{

    val weatherRequest : WeatherApiservice by lazy {
        retrofit.create(WeatherApiservice::class.java)
    }
}
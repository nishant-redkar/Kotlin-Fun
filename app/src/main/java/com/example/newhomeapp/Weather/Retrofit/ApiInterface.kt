package com.example.newhomeapp.Weather.Retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("weather?appid=c85abb81097c19745baf8a863f618405&units=metric")
    fun getWeatherData(@Query("q") name: String): Call<Example>
}


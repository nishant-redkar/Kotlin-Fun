package com.example.newhomeapp.Weather.Retrofit

import com.google.gson.annotations.SerializedName

class Main(
    @SerializedName("temp")
    var temperature: String,

    @SerializedName("humidity")
    var humidity: String,

    @SerializedName("feels_like")
    var feelsLike: String
) {

}

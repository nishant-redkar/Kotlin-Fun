package com.example.newhomeapp.Weather.Retrofit

import com.google.gson.annotations.SerializedName

class Example(
    @SerializedName("main") var main: Main
) {
    fun retrieveMain(): Main {
        return main
    }

    fun updateMain(newMain: Main) {
        main = newMain
    }
}

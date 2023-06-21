package com.example.newhomeapp.Weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.newhomeapp.R
import com.example.newhomeapp.Weather.Retrofit.ApiInterface
import com.example.newhomeapp.Weather.Retrofit.Example
import com.example.weatherapp.Retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherActivity : Fragment() {

    private lateinit var searchView: SearchView
    private lateinit var tempText: TextView
    private lateinit var descText: TextView
    private lateinit var humidityText: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the weather fragment layout
        val view = inflater.inflate(R.layout.activity_weather, container, false)
        searchView = view.findViewById(R.id.search_view)
        tempText = view.findViewById(R.id.tempText)
        descText = view.findViewById(R.id.descText)
        humidityText = view.findViewById(R.id.humidityText)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                getWeatherData(query.trim())
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // Handle text change if needed
                return false
            }
        })

        return view
    }

    private fun getWeatherData(name: String) {
        val apiInterface = ApiClient.getClient().create(ApiInterface::class.java)
        val call: Call<Example> = apiInterface.getWeatherData(name)

        call.enqueue(object : Callback<Example> {
            override fun onResponse(call: Call<Example>, response: Response<Example>) {
                val example: Example? = response.body()
                example?.let {
                    tempText.text = "Temp: ${it.main.temperature}°C"
                    descText.text = "Feels Like: ${it.main.feelsLike}°C"
                    humidityText.text = "Humidity: ${it.main.humidity}%"
                }
            }

            override fun onFailure(call: Call<Example>, t: Throwable) {
                // Handle failure
            }
        })
    }
}

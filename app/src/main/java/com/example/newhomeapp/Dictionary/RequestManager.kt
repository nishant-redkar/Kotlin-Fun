package com.example.newhomeapp.Dictionary

import android.content.Context
import android.widget.Toast
import com.example.newhomeapp.Dictionary.Models.APIResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class RequestManager(private val context: Context) {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.dictionaryapi.dev/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getWordMeaning(listener: OnFetchDataListener, word: String) {
        val callDictionary = retrofit.create(CallDictionary::class.java)
        val call = callDictionary.callMeanings(word)

        try {
            call.enqueue(object : Callback<List<APIResponse>> {
                override fun onResponse(
                    call: Call<List<APIResponse>>,
                    response: Response<List<APIResponse>>
                ) {
                    if (!response.isSuccessful) {
                        Toast.makeText(context, "Error!!", Toast.LENGTH_SHORT).show()
                        return
                    }
                    listener.onFetchData(response.body()?.get(0), response.message())
                }

                override fun onFailure(call: Call<List<APIResponse>>, t: Throwable) {
                    listener.onError("Request Failed!")
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Error Occurred!!", Toast.LENGTH_SHORT).show()
        }
    }

    interface CallDictionary {
        @GET("entries/en/{word}")
        fun callMeanings(@Path("word") word: String): Call<List<APIResponse>>
    }
}
package com.example.newhomeapp.Dictionary

import com.example.newhomeapp.Dictionary.Models.APIResponse

interface OnFetchDataListener {
    fun onFetchData(apiResponse: APIResponse?, message: String)
    fun onError(message: String)
}
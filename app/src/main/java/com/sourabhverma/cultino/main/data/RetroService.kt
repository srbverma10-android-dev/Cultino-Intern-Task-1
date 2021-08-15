package com.sourabhverma.cultino.main.data

import com.sourabhverma.cultino.main.pojo.ResponseFromAPI
import retrofit2.Call
import retrofit2.http.GET

interface RetroService {
    @GET("mandi?lat=28.44108136&lon=77.0526054&ver=89&lang=hi&crop_id=10")
    fun fetchDataFromAPI() : Call<ResponseFromAPI>
}
package com.sourabhverma.cultino.main

import android.util.Log
import androidx.lifecycle.LiveData
import com.sourabhverma.cultino.main.data.DAO
import com.sourabhverma.cultino.main.data.RetroService
import com.sourabhverma.cultino.main.pojo.OtherMandi
import com.sourabhverma.cultino.main.pojo.ResponseFromAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainRepository  @Inject constructor(private val retroService : RetroService, private val dao: DAO){
    fun getDataFromAPI(onResult: (ResponseFromAPI?)->Unit) {
        Log.d("TAG", "getDataFromAPI: ")
        retroService.fetchDataFromAPI().enqueue(object : Callback<ResponseFromAPI>{
            override fun onResponse(
                call: Call<ResponseFromAPI>,
                response: Response<ResponseFromAPI>
            ) {
                val res : ResponseFromAPI? = response.body()
                if(res?.status.equals("success")){
                    Log.d("TAG", "onSuccess:")
                    val thread = Thread {
                        res?.data?.other_mandi?.let {
                            dao.insertAll(it)
                        }
                    }
                    thread.start()
                    onResult(res)
                }
            }
            override fun onFailure(call: Call<ResponseFromAPI>, t: Throwable) {
                Log.d("TAG", "onFailure: ${t.message}")
                onResult(null)
            }
        })
    }
    fun getDataFromRoom() : LiveData<List<OtherMandi>> {
        return dao.getAll()
    }


}
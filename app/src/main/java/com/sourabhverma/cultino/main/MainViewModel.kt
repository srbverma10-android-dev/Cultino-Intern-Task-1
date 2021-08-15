package com.sourabhverma.cultino.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sourabhverma.cultino.main.pojo.OtherMandi
import com.sourabhverma.cultino.main.pojo.ResponseFromAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    private var apiResponse : MutableLiveData<ResponseFromAPI> = MutableLiveData()

    fun onFetchButtonClick(){
        repository.getDataFromAPI {
            apiResponse.postValue(it)
        }
    }
    fun responseFromAPIObserver() : MutableLiveData<ResponseFromAPI> {
        return apiResponse
    }

    fun getDataFromRoom() : LiveData<List<OtherMandi>>{
        return repository.getDataFromRoom()
    }
}
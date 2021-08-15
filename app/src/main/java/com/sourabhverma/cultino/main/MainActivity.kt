package com.sourabhverma.cultino.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sourabhverma.cultino.R
import com.sourabhverma.cultino.databinding.ActivityMainBinding
import com.sourabhverma.cultino.main.adapter.RecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainVM: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = mainVM

        val recyclerView = binding.recyclerView
        val linearLayout = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val recyclerViewAdapter = RecyclerViewAdapter()
        recyclerView.layoutManager = linearLayout

        mainVM.responseFromAPIObserver().observe(this, {
            recyclerViewAdapter.setDataList(it.data.other_mandi)
            recyclerView.adapter = recyclerViewAdapter
        })

        mainVM.getDataFromRoom().observe(this, {
            if (!it.isNullOrEmpty()) {
                recyclerViewAdapter.setDataList(it)
                recyclerView.adapter = recyclerViewAdapter
            }
        })

    }
}
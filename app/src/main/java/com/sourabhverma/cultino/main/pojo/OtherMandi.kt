package com.sourabhverma.cultino.main.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OtherMandi(
    val crop_id: Int,
    val district: String,
    val district_id: Int,
    val hindi_name: String,
    @PrimaryKey
    val id: Int,
    val image: String,
    val km: Double,
    val last_date: String,
    val lat: Double,
    val lng: Double,
    val location: String,
    val market: String,
    val meters: Double,
    val state: String,
    val url_str: String
)
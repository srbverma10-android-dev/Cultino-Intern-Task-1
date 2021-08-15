package com.sourabhverma.cultino.main.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sourabhverma.cultino.main.pojo.OtherMandi

@Dao
interface DAO {
    @Query("SELECT * FROM OtherMandi")
    fun getAll(): LiveData<List<OtherMandi>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(otherMandi: List<OtherMandi>)
}
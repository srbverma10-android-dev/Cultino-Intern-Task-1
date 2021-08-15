package com.sourabhverma.cultino.main.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sourabhverma.cultino.main.pojo.OtherMandi

@Database(entities = [OtherMandi::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun otherMandiDAO(): DAO
}
package com.sourabhverma.cultino.di

import android.content.Context
import androidx.room.Room
import com.sourabhverma.cultino.main.data.AppDatabase
import com.sourabhverma.cultino.main.data.DAO
import com.sourabhverma.cultino.main.data.RetroService
import com.sourabhverma.cultino.utils.CacheHelperClass
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://thekrishi.com/test/"

    @Provides
    fun getRetrofitService(retrofit: Retrofit) : RetroService {
        return retrofit.create(RetroService::class.java)
    }
    @Provides
    fun getRetroInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun getDao(appDatabase: AppDatabase): DAO {
        return appDatabase.otherMandiDAO()
    }
    @Provides
    fun getCacheHelperClass():CacheHelperClass {
        return CacheHelperClass()
    }
    @Provides
    fun getAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "LocalDB"
        ).build()
    }
}
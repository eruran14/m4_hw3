package com.eru.les3_m4

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.eru.les3_m4.room.AppDataBase

class App: Application() {

    companion object {
        lateinit var dataBase: AppDataBase
    }

    override fun onCreate() {
        super.onCreate()
        dataBase = Room
            .databaseBuilder(this, AppDataBase::class.java, "database.db")
            .allowMainThreadQueries()
            .build()
    }
}
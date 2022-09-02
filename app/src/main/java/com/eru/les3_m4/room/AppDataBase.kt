package com.eru.les3_m4.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eru.les3_m4.models.News

@Database(entities = [News::class], version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun newsDao(): NewsDao
}
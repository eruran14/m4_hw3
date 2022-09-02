package com.eru.les3_m4.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.eru.les3_m4.models.News

@Dao
interface NewsDao {

    @Query("SELECT * FROM news")
    fun getAll(): List<News>

    @Insert
    fun insert(news: News)
}
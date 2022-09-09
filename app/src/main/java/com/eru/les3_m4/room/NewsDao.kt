package com.eru.les3_m4.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.eru.les3_m4.models.News

@Dao
interface NewsDao {

    @Query("SELECT * FROM news")
    fun getAll(): List<News>

    @Query("SELECT * FROM news order by createdAt desc")
    fun getAllLive(): LiveData<List<News>>

    @Insert
    fun insert(news: News)

    @Delete
    fun delete(news: News)
}
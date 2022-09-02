package com.eru.les3_m4.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class News(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val createdAt: String?
)



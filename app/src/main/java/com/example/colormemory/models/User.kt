package com.example.colormemory.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    var id: Int,

    @ColumnInfo(name = "user_name")
    val name: String,

    @ColumnInfo(name = "user_score")
    val score: Int

)
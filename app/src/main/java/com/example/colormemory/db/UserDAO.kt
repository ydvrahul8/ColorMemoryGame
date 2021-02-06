package com.example.colormemory.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.colormemory.models.User

@Dao
interface UserDAO {

    @Insert
    suspend fun insertUser(user: User): Long

    @Query("SELECT * FROM user_table ORDER BY user_score DESC")
    fun getAllUser(): LiveData<List<User>>

}
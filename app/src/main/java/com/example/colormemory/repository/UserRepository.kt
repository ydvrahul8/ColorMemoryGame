package com.example.colormemory.repository

import com.example.colormemory.db.UserDAO
import com.example.colormemory.models.User

class UserRepository(private val dao: UserDAO) {

    val users = dao.getAllUser()

    suspend fun insert(user: User): Long {
        return dao.insertUser(user)
    }

}
package com.alexxware.klas.repository

import com.alexxware.klas.model.User

interface UserRepository {
    suspend fun saveUser(user: User): Result<Unit>
}
package com.alexxware.klas.repository

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    suspend fun registerUser(email: String, password: String): Result<FirebaseUser?>
}
package com.alexxware.klas.repository

import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

interface AuthRepository{
    suspend fun registerUser(email: String, password: String): Result<FirebaseUser?>
    suspend fun signOut() //puede ser asíncrona, por eso suspend
}
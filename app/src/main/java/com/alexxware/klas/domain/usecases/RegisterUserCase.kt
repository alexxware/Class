package com.alexxware.klas.domain.usecases

import com.alexxware.klas.data.AuthRepositoryImpl
import com.alexxware.klas.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser

class RegisterUserCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<FirebaseUser?> {
        return repository.registerUser(email, password)
    }
}
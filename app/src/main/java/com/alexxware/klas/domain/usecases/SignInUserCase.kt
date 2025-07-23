package com.alexxware.klas.domain.usecases

import com.alexxware.klas.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class RegisterUserCase @Inject constructor(
    private val repository: AuthRepository
){
    suspend operator fun invoke(email: String, password: String): Result<FirebaseUser?> {
        return repository.registerUser(email, password)
    }
}
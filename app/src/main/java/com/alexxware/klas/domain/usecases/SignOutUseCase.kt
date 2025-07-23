package com.alexxware.klas.domain.usecases

import com.alexxware.klas.repository.AuthRepository
import javax.inject.Inject

class SignOutUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke() {
        return authRepository.signOut()
    }
}
package com.alexxware.klas.domain.usecases

import com.alexxware.klas.model.User
import com.alexxware.klas.repository.UserRepository
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(user: User): Result<Unit> = repository.saveUser(user)
}
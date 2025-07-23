package com.alexxware.klas.data

import com.alexxware.klas.model.User
import com.alexxware.klas.repository.UserRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.util.UUID
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
): UserRepository {
    override suspend fun saveUser(user: User): Result<Unit> {
        return try {
            val userId = UUID.randomUUID().toString()// generamos ID aleatorio
            val userToSet = user.copy(id = userId)//le asignamos al usuario el id random generado
            firestore.collection("users")
                .document(userId)//abrimos el documento con el id random
                .set(userToSet)//le asignamos el usuario
                .await()

            Result.success(Unit)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
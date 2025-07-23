package com.alexxware.klas.di

import com.alexxware.klas.data.AuthRepositoryImpl
import com.alexxware.klas.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/*

### @Binds:### indica que vas a enlazar una implementación concreta (AuthRepositoryImpl) con su interfaz (AuthRepository).

@InstallIn(SingletonComponent::class): hace que este módulo esté disponible a nivel de toda la aplicación.

 */
@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {

    @Binds
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository
}
package com.alexxware.klas.presentation.auth

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(LoginUiState())
    val state: StateFlow<LoginUiState> = _state

    fun onEmailChange(newValue: String){
        _state.update {
            state.value.copy(
                email = newValue,
            )
        }
    }

    fun onPasswordChange(newValue: String){
        _state.update {
            state.value.copy(
                password = newValue,
            )
        }
    }

    fun isEmailValid(email: String): Boolean{
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    //funcion para validar que la contraseña tenga mas de 6 caracteres
    fun isPasswordValid(password: String): Boolean = _state.value.password.length > 8
}

data class LoginUiState(
    val email:String = "",
    val password: String = ""
)
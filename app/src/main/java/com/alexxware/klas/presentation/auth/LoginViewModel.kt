package com.alexxware.klas.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexxware.klas.domain.usecases.RegisterUserCase
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val registerUserCase: RegisterUserCase
) : ViewModel() {
    private val _state = MutableStateFlow(LoginUiState())
    val state: StateFlow<LoginUiState> = _state

    fun login(email: String, password: String) {
        viewModelScope.launch {
            // Activar loading
            _state.value = _state.value.copy(isLoading = true, errorMessage = null)

            val result: Result<FirebaseUser?> = registerUserCase.signIn(email = _state.value.email, password = _state.value.password)
            result.onSuccess {
                _state.value = _state.value.copy(isSuccess = true)
            }
            .onFailure { e ->
                _state.value = _state.value.copy(errorMessage = e.message)
                print(e.message)
            }
            // Desactivar loading
            _state.value = _state.value.copy(isLoading = false)
        }
    }

    fun onEmailChange(newEmail: String){
        val newState = _state.value.copy(email = newEmail)
        _state.value = newState
    }

    fun onPasswordChange(newPassword: String){
        val newState = _state.value.copy(password = newPassword)
        _state.value = newState

    }

    fun clearUiState(){
        _state.value = LoginUiState()
    }

    fun isEmailValid(email: String): Boolean{
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    //funcion para validar que la contraseña tenga mas de 6 caracteres
    fun isPasswordValid(password: String): Boolean = _state.value.password.length > 8
}

data class LoginUiState(
    val email:String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isSuccess: Boolean = false
)
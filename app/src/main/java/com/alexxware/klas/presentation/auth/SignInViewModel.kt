package com.alexxware.klas.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexxware.klas.domain.usecases.RegisterUserCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val registerUserCase: RegisterUserCase
): ViewModel() {
    private val _state = MutableStateFlow(SignInUiState())
    val state: StateFlow<SignInUiState> = _state

    //register user
    private val _registerResult = MutableStateFlow<RegisterUiState>(RegisterUiState.Idle)
    val registerResult: StateFlow<RegisterUiState> = _registerResult

    fun register(email: String, password: String) {
        viewModelScope.launch {
            _registerResult.value = RegisterUiState.Loading //mostramos que estamos cargando en la vista
            val result = registerUserCase.invoke(email = email, password = password)
            result
                .onSuccess { user ->
                    _registerResult.value = RegisterUiState.Success(user)
                }
                .onFailure { error ->
                    _registerResult.value = RegisterUiState.Error(error.message ?: "Error desconocido")
                }
        }
    }
    fun clearUiState() {
        _registerResult.value = RegisterUiState.Idle
    }
    fun onNameChange(text: String){
        _state.update {
            state.value.copy(
                name = text
            )
        }

    }

    fun onEmailChange(text: String){
        _state.update {
            state.value.copy(
                email = text
            )
        }
    }
    fun onPasswordChange(text: String){
        _state.update {
            state.value.copy(
                password = text,
                isPasswordValid = validPassword()
            )
        }
    }
    fun onConfirmPasswordChange(text: String){
        _state.update {
            state.value.copy(
                confirmPassword = text,
                isPasswordValid = validPassword()
            )
        }
    }

    fun validPassword(): Boolean {
        if (_state.value.password.length != _state.value.confirmPassword.length) return false
        return _state.value.password == _state.value.confirmPassword
    }


}

data class SignInUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isPasswordValid: Boolean = false
)

sealed class RegisterUiState {
    object Idle : RegisterUiState()
    object Loading : RegisterUiState()
    data class Success(val user: FirebaseUser?) : RegisterUiState()
    data class Error(val message: String) : RegisterUiState()
}

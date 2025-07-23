package com.alexxware.klas.presentation.auth

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(): ViewModel() {
    private val _state = MutableStateFlow(SignInUiState())
    val state: StateFlow<SignInUiState> = _state

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
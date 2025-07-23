package com.alexxware.klas.presentation.auth

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexxware.klas.domain.usecases.RegisterUserCase
import com.alexxware.klas.domain.usecases.SaveUserUseCase
import com.alexxware.klas.model.User
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
    private val registerUserCase: RegisterUserCase,
    private val saveUserUseCase: SaveUserUseCase
): ViewModel() {
    private val _state = MutableStateFlow(SignInUiState())
    val state: StateFlow<SignInUiState> = _state

    //register user
    private val _registerResult = MutableStateFlow<RegisterUiState>(RegisterUiState.Idle)
    val registerResult: StateFlow<RegisterUiState> = _registerResult

    fun register(email: String, password: String) {
        viewModelScope.launch {
            _registerResult.value =
                RegisterUiState.Loading //mostramos que estamos cargando en la vista
            val result = registerUserCase.invoke(email = email, password = password)
            result
                .onSuccess { user ->


                    //generamos al usuario
                    val userModel = User(
                        id = "",
                        name = _state.value.name,
                        email = _state.value.name
                    )

                    val saveResult = saveUserUseCase.invoke(userModel)
                    if (saveResult.isSuccess){
                        _registerResult.value = RegisterUiState.Success(user)
                    } else {
                        _registerResult.value =
                            RegisterUiState.Error(saveResult.exceptionOrNull()?.message ?: "Error desconocido")
                    }
                }
                .onFailure { error ->
                    _registerResult.value =
                        RegisterUiState.Error(error.message ?: "Error desconocido")
                }
        }
    }

    fun clearUiState() {
        _registerResult.value = RegisterUiState.Idle
    }
    fun onNameChange(text: String) {
        val newState = _state.value.copy(name = text)
        _state.value = newState.copy(isPasswordValid = isValidForm(newState))
    }
    fun onEmailChange(text: String){
        val newState = _state.value.copy(email = text)
        _state.value = newState.copy(isPasswordValid = isValidForm(newState))
    }
    fun onPasswordChange(text: String){
        val newState = _state.value.copy(password = text)
        _state.value = newState.copy(isPasswordValid = isValidForm(newState))
    }
    fun onConfirmPasswordChange(text: String){
        val newState = _state.value.copy(confirmPassword = text)
        _state.value = newState.copy(isPasswordValid = isValidForm(newState))
    }
    fun isValidForm(state: SignInUiState): Boolean {
        return with(state) {
            name.isNotBlank() &&
            email.isNotBlank() &&
            Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
            password == confirmPassword &&
            password.length >= 8
        }
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

package com.alexxware.klas.presentation.content

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexxware.klas.domain.usecases.SignOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val signOutUseCase: SignOutUseCase
): ViewModel() {

    //funcion de cerrar sesion
    fun signOut(){
        viewModelScope.launch {
            signOutUseCase()
        }
    }
}
package com.alexxware.klas.presentation.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alexxware.klas.R
import com.alexxware.klas.ui.components.InputSecure
import com.alexxware.klas.ui.components.InputText
import com.alexxware.klas.ui.components.TextSubTitleCompose
import com.alexxware.klas.ui.components.TextTitleCompose
import com.alexxware.klas.ui.components.buttons.ButtonPrimary
import com.alexxware.klas.ui.components.buttons.OutlinedButtonPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    signInViewModel: SignInViewModel = hiltViewModel(),
    navigateToLogin: () -> Unit
) {
    val viewModel = signInViewModel.state.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBack()
                        }
                    ) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { values ->
        Column(
            modifier = Modifier
                .padding(values)
                .padding(16.dp)
        ) {
            Spacer(modifier = modifier.weight(1f))
            TextTitleCompose(stringResource(R.string.register_title))
            Spacer(modifier = modifier.weight(1f))
            //Nombre de usuario
            TextSubTitleCompose(stringResource(R.string.register_name))
            InputText(
                value = viewModel.value.name,
                onValueChange = { signInViewModel.onNameChange(it) }
            )
            //correo electronico
            Spacer(modifier = Modifier.height(16.dp))
            TextSubTitleCompose(stringResource(R.string.email))
            InputText(
                value = viewModel.value.email,
                onValueChange = { signInViewModel.onEmailChange(it) }
            )
            //contraseña
            Spacer(modifier = Modifier.height(16.dp))
            TextSubTitleCompose(stringResource(R.string.password))
            InputSecure(
                value = viewModel.value.password,
                onValueChange = { signInViewModel.onPasswordChange(it) }
            )
            //confirmar contraseña
            Spacer(modifier = Modifier.height(16.dp))
            TextSubTitleCompose(stringResource(R.string.register_confirmPassword))
            InputSecure(
                value = viewModel.value.confirmPassword,
                onValueChange = { signInViewModel.onConfirmPasswordChange(it) }
            )

            Spacer(modifier = modifier.weight(1f))
            //boton de iniciar sesion
            OutlinedButtonPrimary(
                text = stringResource(R.string.inicioS),
                onClick = {
                    navigateToLogin()
                }
            )
            //boton de registrarse
            ButtonPrimary(
                text = stringResource(R.string.register),
                onClick = {},
                enabled = viewModel.value.isPasswordValid
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
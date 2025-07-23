package com.alexxware.klas.presentation.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
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
import com.alexxware.klas.ui.components.buttons.TextButtonPrimary

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = hiltViewModel(),
    navigateTo: () -> Unit
) {
    val uiState = loginViewModel.state.collectAsState()

    Column(
        modifier = modifier.padding(16.dp)
    ) {
        Spacer(modifier = modifier.weight(1f))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.resource_class),
                contentDescription = stringResource(R.string.app_name),
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )
        }
        Spacer(modifier = modifier.weight(1f))
        TextTitleCompose(stringResource(R.string.inicioS))
        Spacer(modifier = Modifier.height(16.dp))

        TextSubTitleCompose(stringResource(R.string.email))
        InputText(
            value = uiState.value.email,
            onValueChange = { loginViewModel.onEmailChange(it) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextSubTitleCompose(stringResource(R.string.password))
        /*InputText(
            value = uiState.value.password,
            onValueChange = { loginViewModel.onPasswordChange(it) }
        )*/
        InputSecure(
            value = uiState.value.password,
            onValueChange = { loginViewModel.onPasswordChange(it) }
        )
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ){
            TextButtonPrimary(
                text = stringResource(R.string.forgot_password),
                onClick = {}
            )
        }

        Spacer(modifier = modifier.weight(1f))

        OutlinedButtonPrimary(
            text = stringResource(R.string.register),
            onClick = {
                navigateTo()
            }
        )
        ButtonPrimary(
            text = stringResource(R.string.login),
            onClick = {}
        )
        Spacer(modifier = Modifier.height(16.dp))

    }
}
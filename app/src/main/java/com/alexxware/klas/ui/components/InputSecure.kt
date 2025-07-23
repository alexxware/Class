package com.alexxware.klas.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicSecureTextField
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun InputSecure(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val icon = if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
            IconButton(
                onClick = {
                    isPasswordVisible = !isPasswordVisible
                }
            ) {
                Icon(imageVector = icon, contentDescription = null)
            }
        }
    )
}
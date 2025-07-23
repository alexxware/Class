package com.alexxware.klas.ui.components.buttons

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TextButtonPrimary(text: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    TextButton(
        onClick = {},
        modifier = modifier
    ) {
        Text(text = text)
    }
}
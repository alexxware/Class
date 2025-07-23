package com.alexxware.klas.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.alexxware.klas.ui.theme.bodyFontFamily

@Composable
fun TextSubTitleCompose(text: String){
    Text(
        text = text,
        maxLines = 1,
        style = MaterialTheme.typography.bodyLarge,
        fontFamily = bodyFontFamily,
        fontSize = 28.sp,
        modifier = Modifier.fillMaxWidth()
    )
}
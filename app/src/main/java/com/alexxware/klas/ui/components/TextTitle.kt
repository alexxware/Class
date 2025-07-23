package com.alexxware.klas.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.alexxware.klas.ui.theme.bodyFontFamily

@Composable
fun TextTitleCompose(text: String){
    Text(
        text = text,
        maxLines = 1,
        style = MaterialTheme.typography.titleLarge,
        fontFamily = bodyFontFamily,
        fontSize = 48.sp,
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview
@Composable
private fun PrevTitle() {
    val text = "Class"
    TextTitleCompose(text)
}
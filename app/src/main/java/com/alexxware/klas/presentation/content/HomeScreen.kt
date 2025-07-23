package com.alexxware.klas.presentation.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.firebase.auth.FirebaseAuth

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Scaffold { values ->
        Box(
            modifier = Modifier.padding(values),
            contentAlignment = Alignment.Center
        ){
            Text("Home Screen")
            Button(
                onClick = {

                }
            ) {
                Text("Salir")
            }
        }
    }
}
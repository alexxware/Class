package com.alexxware.klas.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alexxware.klas.presentation.auth.LoginScreen
import com.alexxware.klas.presentation.auth.SignInScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Login::class.java.simpleName
    ) {
        composable(Login::class.java.simpleName){
            LoginScreen(modifier, navigateTo = { navController.navigate(SignIn::class.java.simpleName)})
        }
        composable(SignIn::class.java.simpleName) {
            SignInScreen(
                onBack = {
                    navController.popBackStack()
                },
                navigateToLogin = {
                    navController.popBackStack()
                }
            )
        }
    }
}
package com.alexxware.klas.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alexxware.klas.presentation.auth.LoginScreen
import com.alexxware.klas.presentation.auth.SignInScreen
import com.alexxware.klas.presentation.content.HomeScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    auth: FirebaseAuth
) {
    val navController = rememberNavController()
    val currentUser = auth.currentUser
    NavHost(
        navController = navController,
        startDestination = if(currentUser != null) Home::class.java.simpleName else Login::class.java.simpleName
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
                },
                onRegisterSuccess = {
                    navController.navigate(Home::class.java.simpleName)
                }
            )
        }
        composable(Home::class.java.simpleName) {
            HomeScreen(
                onBackToLogin = {
                    navController.navigate(Login::class.java.simpleName){
                        popUpTo(Home::class.java.simpleName){
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}
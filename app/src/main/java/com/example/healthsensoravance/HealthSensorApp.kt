package com.example.healthsensoravance

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.healthsensoravance.screens.LoginComponent
import com.example.healthsensoravance.screens.RegisterComponent
import com.example.healthsensoravance.screens.SplashComponent

@Composable
fun HealthSensorApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.SPLASH) {

        composable(Routes.SPLASH) {
            SplashComponent(onTimeout = {
                // TODO: Aquí puedes añadir lógica para ver si ya hay un usuario logueado
                navController.navigate(Routes.LOGIN) {
                    popUpTo(Routes.SPLASH) { inclusive = true }
                }
            })
        }

        composable(Routes.LOGIN) {
            LoginComponent(
                navController = navController,
                onLoginSuccess = {
                    navController.navigate(Routes.PRONOSTICO) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.REGISTER) {
            RegisterComponent(
                navController = navController,
                onRegisterSuccess = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.REGISTER) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.PRONOSTICO) {
            MainAppNavigation()
        }
    }
}
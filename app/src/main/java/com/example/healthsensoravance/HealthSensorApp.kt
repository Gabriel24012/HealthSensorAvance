package com.example.healthsensoravance

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.healthsensoravance.screens.SplashComponent
import com.example.healthsensoravance.screens.LoginComponent
import com.example.healthsensoravance.screens.RegisterComponent
import com.example.healthsensoravance.screens.PronosticoScreen
import com.example.healthsensoravance.screens.ArchivoScreen
import com.example.healthsensoravance.screens.ConfiguracionScreen
import com.example.healthsensoravance.screens.FichaMedicaScreen
import com.example.healthsensoravance.screens.RecordatoriosScreen
import com.example.healthsensoravance.screens.PerfilScreen
import com.example.healthsensoravance.screens.DatosSaludScreen
import com.example.healthsensoravance.components.MainAppLayout
import com.example.healthsensoravance.screens.QR

@Composable
    fun HealthSensorApp() {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = Routes.SPLASH) {
            composable(Routes.SPLASH) {
                SplashComponent(onTimeout = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.SPLASH) { inclusive = true }
                    }
                }) }
            composable(Routes.LOGIN) { LoginComponent(navController) }
            composable(Routes.REGISTER) { RegisterComponent(navController) }
            composable(Routes.PRONOSTICO) { MainAppLayout(navController) { padding -> PronosticoScreen(padding) } }
            composable(Routes.ARCHIVO) { MainAppLayout(navController) { padding -> ArchivoScreen(navController, padding) } }
            composable(Routes.CONFIGURACION) { MainAppLayout(navController) { padding -> ConfiguracionScreen(navController, padding) } }
            composable(Routes.FICHA_MEDICA) { FichaMedicaScreen(navController) }
            composable(Routes.RECORDATORIOS) { RecordatoriosScreen(navController) }
            composable(Routes.PERFIL) { PerfilScreen(navController) }
            composable(Routes.DATOS_SALUD) { DatosSaludScreen(navController) }
            composable(Routes.QR) { QR(navController) }

        }
    }


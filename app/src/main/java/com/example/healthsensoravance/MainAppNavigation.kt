package com.example.healthsensoravance

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.healthsensoravance.components.MainAppLayout
import com.example.healthsensoravance.screens.* // Importa todas tus pantallas

@Composable
fun MainAppNavigation() {
    val nestedNavController = rememberNavController()

    MainAppLayout(navController = nestedNavController) { padding ->

        NavHost(
            navController = nestedNavController,
            startDestination = Routes.PRONOSTICO // Pestaña por defecto
        ) {

            composable(Routes.PRONOSTICO) {
                PronosticoScreen(nestedNavController, padding)
            }
            composable(Routes.OPCIONES) {
                OpcionesScreen(nestedNavController, padding)
            }
            composable(Routes.CONFIGURACION) {
                ConfiguracionScreen(nestedNavController, padding)
            }

            composable(Routes.FICHA_MEDICA) {
                FichaMedicaScreen(nestedNavController, padding)
            }
            composable(Routes.RECORDATORIOS) {
                RecordatoriosScreen(nestedNavController, padding)
            }
            composable(Routes.QR) {
                QR(nestedNavController)
            }
            composable(Routes.SyncData) {
                SincronizarDScreen(nestedNavController, padding)
            }
            composable(Routes.REGISTROS) {
                RegistrosScreen(nestedNavController, padding)
            }
            composable(Routes.ACCESIBILIDAD) {
                AcceScreen(nestedNavController)
            }
            composable(Routes.PERFIL) {
                PerfilScreen(nestedNavController)
            }
            composable(Routes.DATOS_SALUD) {
                DatosSaludScreen(nestedNavController)
            }
            composable(Routes.SOPORTE) {
                SoporteScreen(nestedNavController)
            }

        }
    }
}
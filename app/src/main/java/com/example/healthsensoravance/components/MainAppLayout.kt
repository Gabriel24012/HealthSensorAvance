package com.example.healthsensoravance.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import com.example.healthsensoravance.Routes

@Composable
fun MainAppLayout(
    navController: NavHostController,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        bottomBar = { AppBottomNavigationBar(navController) }
    ) { paddingValues ->
        content(paddingValues)
    }
}
sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Pronostico : BottomNavItem(Routes.PRONOSTICO, Icons.Default.DateRange, "Pronóstico")
    object Archivo : BottomNavItem(Routes.OPCIONES, Icons.Default.Menu, "Opciones")
    object Configuracion : BottomNavItem(Routes.CONFIGURACION, Icons.Default.Settings, "Ajustes")
}
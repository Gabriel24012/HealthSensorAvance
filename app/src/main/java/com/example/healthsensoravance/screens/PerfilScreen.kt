package com.example.healthsensoravance.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.healthsensoravance.components.BaseContentScreen

@Composable
fun PerfilScreen(navController: NavHostController) {
    BaseContentScreen(title = "Editar Perfil y Contactos", showBack = true, navController = navController) {
        Text("Actualiza tu información personal y contactos de emergencia.", Modifier.padding(16.dp))
    }
}

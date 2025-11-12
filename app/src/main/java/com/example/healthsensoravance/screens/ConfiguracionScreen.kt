package com.example.healthsensoravance.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.ui.platform.LocalConfiguration
import com.example.healthsensoravance.Routes
import com.example.healthsensoravance.components.BaseContentScreen
import com.example.healthsensoravance.components.SettingItem

@Composable
fun ConfiguracionScreen(navController: NavHostController, paddingValues: PaddingValues) {
    BaseContentScreen(title = "Ajustes", paddingValues = paddingValues) {
        val configuration = LocalConfiguration.current
        val isLandscape = configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = if (isLandscape) 32.dp else 16.dp)
        ) {
            SettingItem("Editar Perfil y Contactos", Icons.Default.AccountCircle, onClick = { navController.navigate(
                Routes.PERFIL) })
            Divider(color = Color.LightGray.copy(alpha = 0.3f))
            SettingItem("Modificar Datos de Salud", Icons.Default.Favorite, onClick = { navController.navigate(Routes.DATOS_SALUD) })
            Divider(color = Color.LightGray.copy(alpha = 0.3f))
            SettingItem("Soporte y Ayuda", Icons.Default.Info, onClick = {  })
            Divider(color = Color.LightGray.copy(alpha = 0.3f))
            SettingItem("Accesibilidad", Icons.Default.AccountCircle, onClick = { navController.navigate(Routes.ACCESIBILIDAD) })
        }
    }
}
package com.example.healthsensoravance.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.ui.platform.LocalConfiguration
import com.example.healthsensoravance.Routes
import com.example.healthsensoravance.components.BaseContentScreen
import com.example.healthsensoravance.components.FeatureCard
@Composable
fun ArchivoScreen(navController: NavHostController, paddingValues: PaddingValues) {
    BaseContentScreen(title = "Archivo y Documentación", paddingValues = paddingValues) {
        val isLandscape = LocalConfiguration.current.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE
        val gridItems = listOf(
            Triple("Ficha Médica", Icons.Default.FavoriteBorder, Routes.FICHA_MEDICA),
            Triple("Recordatorios", Icons.Default.DateRange, Routes.RECORDATORIOS)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = if (isLandscape) Arrangement.SpaceEvenly else Arrangement.SpaceBetween
        ) {
            gridItems.forEach { (label, icon, route) ->
                FeatureCard(
                    label = label,
                    icon = icon,
                    onClick = { navController.navigate(route) },
                    modifier = if (isLandscape) Modifier.width(200.dp) else Modifier.width(150.dp)
                )
            }
        }
    }
}

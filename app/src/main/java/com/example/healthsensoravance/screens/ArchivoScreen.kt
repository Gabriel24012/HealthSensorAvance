// Archivo: com/example/healthsensoravance/screens/ArchivoScreen.kt
package com.example.healthsensoravance.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.healthsensoravance.Routes
import com.example.healthsensoravance.components.BaseContentScreen
import com.example.healthsensoravance.components.FeatureCard // <-- Asegúrate de que esta importación sea correcta
import androidx.compose.ui.Alignment

@Composable
fun ArchivoScreen(navController: NavHostController, paddingValues: PaddingValues) {
    BaseContentScreen(title = "Opciones", paddingValues = paddingValues) {

        val gridItems = listOf(
            Triple("Ficha Médica", Icons.Default.FavoriteBorder, Routes.FICHA_MEDICA),
            Triple("Recordatorios", Icons.Default.DateRange, Routes.RECORDATORIOS),
            Triple("QR", Icons.Default.QrCodeScanner, Routes.QR),
            Triple("Sincronizar Datos", Icons.Default.Sync, Routes.QR),
            Triple("Registros", Icons.Default.Assessment, Routes.QR)
        )

        val itemPairs = gridItems.chunked(2)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            itemPairs.forEach { pair ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    pair.forEach { (label, icon, route) ->
                        // Aquí usamos el FeatureCard con el nuevo estilo
                        FeatureCard(
                            label = label,
                            icon = icon,
                            onClick = { navController.navigate(route) },
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                        )
                    }

                    if (pair.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}
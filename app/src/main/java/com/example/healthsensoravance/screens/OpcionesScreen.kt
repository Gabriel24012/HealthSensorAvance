package com.example.healthsensoravance.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.healthsensoravance.Routes
import com.example.healthsensoravance.components.BaseContentScreen
import com.example.healthsensoravance.components.FeatureCard
import androidx.compose.ui.Alignment

@Composable
fun OpcionesScreen(navController: NavHostController, paddingValues: PaddingValues) {
    BaseContentScreen(title = "Opciones", paddingValues = paddingValues) {

        val gridItems = listOf(
            Triple("Ficha Médica", Icons.Default.FavoriteBorder, Routes.FICHA_MEDICA),
            Triple("Recordatorios", Icons.Default.DateRange, Routes.RECORDATORIOS),
            Triple("QR", Icons.Default.QrCodeScanner, Routes.QR),
            Triple("Sincronizar Datos", Icons.Default.Sync, Routes.SyncData),
            Triple("Registros", Icons.Default.Assessment, Routes.REGISTROS)
        )

        val configuration = LocalConfiguration.current
        val orientation = configuration.orientation

        val (numColumns, rowHeight) = if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Pair(4, 200.dp)
        } else {
            Pair(2, 180.dp)
        }

        val itemPairs = gridItems.chunked(numColumns)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            itemPairs.forEach { pair ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(rowHeight),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    pair.forEach { (label, icon, route) ->
                        FeatureCard(
                            label = label,
                            icon = icon,
                            onClick = { navController.navigate(route) },
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                        )
                    }

                    if (pair.size < numColumns) {
                        val spacersToAdd = numColumns - pair.size
                        repeat(spacersToAdd) {
                            Spacer(modifier = Modifier.weight(1f).fillMaxHeight())
                        }
                    }
                }
            }
        }
    }
}
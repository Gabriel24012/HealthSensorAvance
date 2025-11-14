package com.example.healthsensoravance.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CloudDone
import androidx.compose.material.icons.outlined.CloudSync
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.healthsensoravance.components.BaseContentScreen

@Composable
fun SincronizarDScreen(navController: NavHostController, paddingValues: PaddingValues) {
    var isSyncing by remember { mutableStateOf(false) }
    var lastSync by remember { mutableStateOf("Nunca") }
    var syncStatus by remember { mutableStateOf("Sin sincronizar") }
    var syncSuccess by remember { mutableStateOf<Boolean?>(null) }

    LaunchedEffect(isSyncing) {
        if (isSyncing) {
            syncStatus = "Sincronizando..."
            kotlinx.coroutines.delay(2000)
            isSyncing = false
            syncSuccess = true
            syncStatus = "Sincronización exitosa"
            lastSync = "12/11/2025 22:51"
        }
    }

    BaseContentScreen(title = "Sincronización de Datos", paddingValues = paddingValues) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = when (syncSuccess) {
                    true -> Icons.Outlined.CloudDone
                    false -> Icons.Outlined.ErrorOutline
                    else -> Icons.Outlined.CloudSync
                },
                contentDescription = "Estado de sincronización",
                tint = Color(0xFF3F51B5),
                modifier = Modifier.size(64.dp)
            )

            Text("Estado: $syncStatus", style = MaterialTheme.typography.titleMedium, fontSize = 18.sp)
            Text("Última sincronización: $lastSync", style = MaterialTheme.typography.bodyMedium)

            Button(
                onClick = { isSyncing = true },
                enabled = !isSyncing,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3F51B5))
            ) {
                Text("Sincronizar ahora", fontSize = 16.sp)
            }

            Divider()

            Text("Datos sincronizados:", style = MaterialTheme.typography.titleSmall)
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("✔ Ficha Médica")
                Text("✔ Recordatorios")
                Text("✔ Código QR")
            }
        }
    }
}
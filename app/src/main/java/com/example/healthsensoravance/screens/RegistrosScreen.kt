package com.example.healthsensoravance.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Sync
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.healthsensoravance.components.BaseContentScreen

data class RegistroItem(
    val tipo: String,
    val descripcion: String,
    val fecha: String,
    val icono: @Composable () -> Unit
)

@Composable
fun RegistrosScreen(navController: NavHostController, paddingValues: PaddingValues) {
    val registros = remember {
        listOf(
            RegistroItem(
                tipo = "Sincronización",
                descripcion = "Datos sincronizados con el servidor",
                fecha = "12/11/2025 22:51",
                icono = { Icon(Icons.Outlined.Sync, contentDescription = null, tint = Color(0xFF3F51B5)) }
            ),
            RegistroItem(
                tipo = "Ficha Médica",
                descripcion = "Actualización en alergias",
                fecha = "12/11/2025 21:30",
                icono = { Icon(Icons.Outlined.Warning, contentDescription = null, tint = Color(0xFFF44336)) }
            ),
            RegistroItem(
                tipo = "QR generado",
                descripcion = "Código QR creado para consulta médica",
                fecha = "12/11/2025 20:15",
                icono = { Icon(Icons.Outlined.CheckCircle, contentDescription = null, tint = Color(0xFF4CAF50)) }
            )
        )
    }

    BaseContentScreen(title = "Registros", paddingValues = paddingValues) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(registros) { registro ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        registro.icono()
                        Spacer(Modifier.width(12.dp))
                        Column {
                            Text(registro.tipo, style = MaterialTheme.typography.titleMedium)
                            Text(registro.descripcion, style = MaterialTheme.typography.bodyMedium)
                            Text(registro.fecha, style = MaterialTheme.typography.labelSmall, fontSize = 12.sp)
                        }
                    }
                }
            }
        }
    }
}

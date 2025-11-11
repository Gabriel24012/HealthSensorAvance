package com.example.healthsensoravance.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.healthsensoravance.components.BaseContentScreen

@Composable
fun RecordatoriosScreen(navController: NavHostController) {
    BaseContentScreen(title = "Recordatorios Médicos", showBack = true, navController = navController) {
        Text("Gestiona recordatorios de medicinas, citas o ejercicios.", Modifier.padding(16.dp))
    }
}
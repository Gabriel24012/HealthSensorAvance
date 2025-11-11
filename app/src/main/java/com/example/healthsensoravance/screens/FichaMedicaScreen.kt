package com.example.healthsensoravance.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.healthsensoravance.components.BaseContentScreen


@Composable
fun FichaMedicaScreen(navController: NavHostController) {
    BaseContentScreen(title = "Ficha Médica Personal", showBack = true, navController = navController) {
        Text("Aquí se gestionan tus datos médicos, alergias, tipo de sangre, etc.", Modifier.padding(16.dp))
    }
}

// Archivo: com/example/healthsensoravance/components/BaseContentScreen.kt
package com.example.healthsensoravance.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text // Asegúrate de usar Material 3 si es posible
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BaseContentScreen(
    title: String,
    paddingValues: PaddingValues, // Importante para el BottomBar
    content: @Composable () -> Unit
) {
    // Definimos tu color de marca
    val brandBlue = Color(0xFF3F51B5)

    Column(
        modifier = Modifier
            .fillMaxSize()
            // 1. Aplicamos el padding de la BottomBar (pasado desde el Scaffold principal)
            //    para que el contenido no se oculte detrás de la barra de navegación.
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // --- ESTE ES EL TÍTULO ESTILIZADO ---
        Text(
            text = title,
            fontSize = 28.sp, // Tamaño de fuente más grande
            fontWeight = FontWeight.Bold, // Texto en negrita
            color = brandBlue, // Tu color azul
            textAlign = TextAlign.Center, // Título centrado
            modifier = Modifier
                .fillMaxWidth()
                // 2. Damos un padding propio al título para separarlo del borde
                //    y del contenido que viene debajo.
                .padding(top = 24.dp, bottom = 24.dp, start = 16.dp, end = 16.dp)
        )

        // --- Aquí se inserta el contenido de cada pantalla ---
        // 3. El 'content' (ej. la cuadrícula de botones de ArchivoScreen)
        //    se inserta aquí. 'ArchivoScreen' sigue siendo responsable
        //    de su propio padding interno (el .padding(16.dp) de su Column).
        content()
    }
}
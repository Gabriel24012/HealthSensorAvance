// Archivo: com/example/healthsensoravance/components/FeatureCard.kt
package com.example.healthsensoravance.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.* // Usamos Material 3 para los colores y algunos componentes si es posible.
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FeatureCard(
    label: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Color principal de tu marca (azul #2563EB)
    val brandBlue = Color(0xFF3F51B5) // Convierte el hex a un Color de Compose

    // Estado para detectar si el botón está presionado
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    // Define los colores y el borde basado en si está presionado
    val containerColor = if (isPressed) Color.White else brandBlue // Blanco si está presionado, azul si no
    val contentColor = if (isPressed) brandBlue else Color.White // Azul si está presionado, blanco si no
    val borderColor = if (isPressed) brandBlue else Color.Transparent // Borde azul si está presionado

    Card(
        modifier = modifier
            .aspectRatio(1f) // Para que sea cuadrado o casi cuadrado, haciendo el contenido más centrado
            .fillMaxWidth(), // Ocupa el ancho disponible
        shape = RoundedCornerShape(12.dp), // Esquinas redondeadas
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        border = BorderStroke(2.dp, borderColor), // Borde dinámico
        onClick = onClick,
        interactionSource = interactionSource // Asocia el InteractionSource al Card
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp), // Espaciado interno
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                modifier = Modifier.size(48.dp) // Icono más grande
            )
            Spacer(modifier = Modifier.height(8.dp)) // Espacio entre icono y texto
            Text(
                text = label,
                fontSize = 18.sp, // Texto más grande
                fontWeight = FontWeight.SemiBold, // Un poco más audaz
                textAlign = TextAlign.Center,
                lineHeight = 24.sp // Para evitar que se amontone si es de dos líneas
            )
        }
    }
}
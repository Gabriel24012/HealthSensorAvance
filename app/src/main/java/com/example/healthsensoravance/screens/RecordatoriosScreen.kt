package com.example.healthsensoravance.screens

import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import java.util.Calendar

// Data class simple para representar un recordatorio
data class MedicationReminder(val name: String, val time: String)

@Composable
fun RecordatoriosScreen(navController: NavHostController) {
    // --- Estado de la UI ---

    // Lista mutable para guardar los recordatorios.
    // Usamos remember para que persista durante recomposiciones.
    val reminders = remember {
        mutableStateListOf(
            MedicationReminder("Paracetamol 500mg", "08:00"),
            MedicationReminder("Ibuprofeno 400mg", "14:00")
        )
    }

    // Estado para los campos de texto del formulario
    var medicationName by remember { mutableStateOf("") }
    var medicationTime by remember { mutableStateOf("") }

    // Contexto para mostrar el TimePickerDialog
    val context = LocalContext.current

    // --- Layout de la UI ---

    // Columna principal que organiza la pantalla verticalmente
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp) // Espacio entre elementos
    ) {

        // Título de la pantalla
        Text(
            text = "Recordatorios",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0D47A1) // Azul oscuro para el título
        )

        // Sección "Medicamentos actuales"
        Text(
            text = "Medicamentos actuales",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        // Tarjeta para la lista de medicamentos
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Itera sobre la lista de recordatorios y muestra cada uno
                reminders.forEach { reminder ->
                    ReminderItem(reminder = reminder)
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp)) // Espacio extra

        // Sección "Agregar medicamento"
        Text(
            text = "Agregar medicamento",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        // Campo de texto para el nombre
        OutlinedTextField(
            value = medicationName,
            onValueChange = { medicationName = it },
            label = { Text("Nombre del medicamento") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        // Campo de texto para la hora (falso)
        // Se deshabilita para escritura y se usa clickable para abrir el selector de hora
        OutlinedTextField(
            value = medicationTime,
            onValueChange = { }, // No hace nada al escribir
            label = { Text("Hora (HH:mm)") },
            placeholder = { Text("--:--") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClickLabel = "Seleccionar hora") {
                    // Muestra el TimePicker al hacer clic
                    showTimePicker(context) { hour, minute ->
                        medicationTime = String.format("%02d:%02d", hour, minute)
                    }
                },
            readOnly = true, // El usuario no puede escribir
            enabled = false, // Deshabilitado, pero con colores personalizados
            colors = OutlinedTextFieldDefaults.colors(
                disabledTextColor = MaterialTheme.colorScheme.onSurface,
                disabledBorderColor = MaterialTheme.colorScheme.outline,
                disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        )

        // Botón "Guardar Recordatorio"
        Button(
            onClick = {
                // Lógica simple para agregar a la lista (sin validación)
                if (medicationName.isNotBlank() && medicationTime.isNotBlank()) {
                    reminders.add(MedicationReminder(medicationName, medicationTime))
                    // Limpia los campos
                    medicationName = ""
                    medicationTime = ""
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF304FFE)) // Azul
        ) {
            Text(text = "Guardar Recordatorio", Modifier.padding(vertical = 8.dp))
        }

        // Botón "Probar Notificación"
        Button(
            onClick = { /* Aquí iría la lógica para probar una notificación */ },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)) // Verde
        ) {
            Text(text = "Probar Notificación", Modifier.padding(vertical = 8.dp))
        }
    }
}

/**
 * Composable para mostrar un solo ítem de recordatorio en la lista.
 */
@Composable
private fun ReminderItem(reminder: MedicationReminder) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Nombre del medicamento
        Text(
            text = reminder.name,
            style = MaterialTheme.typography.bodyLarge
        )

        // "Pastilla" azul con la hora
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color(0xFF304FFE), // Azul
            contentColor = Color.White // Texto blanco
        ) {
            Text(
                text = reminder.time,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

/**
 * Función helper para mostrar un TimePickerDialog.
 */
private fun showTimePicker(
    context: Context,
    onTimeSelected: (Int, Int) -> Unit
) {
    val calendar = Calendar.getInstance()
    TimePickerDialog(
        context,
        { _, hourOfDay, minute ->
            onTimeSelected(hourOfDay, minute)
        },
        calendar.get(Calendar.HOUR_OF_DAY),
        calendar.get(Calendar.MINUTE),
        true // true para formato 24 horas
    ).show()
}
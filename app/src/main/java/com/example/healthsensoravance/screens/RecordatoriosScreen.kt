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

data class MedicationReminder(val name: String, val time: String)

@Composable
fun RecordatoriosScreen(navController: NavHostController) {

    //  remember para que persista durante recomposiciones.
    val reminders = remember {
        mutableStateListOf(
            MedicationReminder("Paracetamol 500mg", "08:00"),
            MedicationReminder("Ibuprofeno 400mg", "14:00")
        )
    }

    var medicationName by remember { mutableStateOf("") }
    var medicationTime by remember { mutableStateOf("") }

    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp) // Espacio entre elementos
    ) {

        Text(
            text = "Recordatorios",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0D47A1) // Azul oscuro para el título
        )

        Text(
            text = "Medicamentos actuales",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

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
                reminders.forEach { reminder ->
                    ReminderItem(reminder = reminder)
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Agregar medicamento",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        OutlinedTextField(
            value = medicationName,
            onValueChange = { medicationName = it },
            label = { Text("Nombre del medicamento") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = medicationTime,
            onValueChange = { },
            label = { Text("Hora (HH:mm)") },
            placeholder = { Text("--:--") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClickLabel = "Seleccionar hora") {
                    showTimePicker(context) { hour, minute ->
                        medicationTime = String.format("%02d:%02d", hour, minute)
                    }
                },
            readOnly = true,
            enabled = false,
            colors = OutlinedTextFieldDefaults.colors(
                disabledTextColor = MaterialTheme.colorScheme.onSurface,
                disabledBorderColor = MaterialTheme.colorScheme.outline,
                disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        )

        Button(
            onClick = {
                if (medicationName.isNotBlank() && medicationTime.isNotBlank()) {
                    reminders.add(MedicationReminder(medicationName, medicationTime))
                    medicationName = ""
                    medicationTime = ""
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF304FFE))
        ) {
            Text(text = "Guardar Recordatorio", Modifier.padding(vertical = 8.dp))
        }

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


@Composable
private fun ReminderItem(reminder: MedicationReminder) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = reminder.name,
            style = MaterialTheme.typography.bodyLarge
        )

        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color(0xFF304FFE),
            contentColor = Color.White
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
        true
    ).show()
}
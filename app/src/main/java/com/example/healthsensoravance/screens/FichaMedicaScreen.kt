package com.example.healthsensoravance.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

// --- CAMBIOS CLAVE ---
import androidx.compose.foundation.layout.PaddingValues // 1. Importar PaddingValues
import com.example.healthsensoravance.components.BaseContentScreen // 2. Importar tu plantilla

@Composable
fun FichaMedicaScreen(
    navController: NavHostController,
    paddingValues: PaddingValues // 3. Recibir los PaddingValues
) {
    // Todas tus variables 'remember' se quedan igual
    var edad by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    var altura by remember { mutableStateOf("") }
    var selectedBloodType by remember { mutableStateOf<String?>(null) }
    val bloodTypes = listOf("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-")
    var hasAlergias by remember { mutableStateOf(true) }
    var alergiasDetails by remember { mutableStateOf("") }
    var hasEnfermedades by remember { mutableStateOf(true) }
    var enfermedadesDetails by remember { mutableStateOf("") }
    var hasMedicamentos by remember { mutableStateOf(true) }
    var medicamentosDetails by remember { mutableStateOf("") }
    var nss by remember { mutableStateOf("") }
    var hasSeguro by remember { mutableStateOf(true) }
    var hasCirugias by remember { mutableStateOf(true) }
    var cirugiasDetails by remember { mutableStateOf("") }

    // 4. Usar la plantilla BaseContentScreen
    BaseContentScreen(title = "Ficha Médica", paddingValues = paddingValues) {

        // 5. El Column ahora va adentro y es el 'content'
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                // 6. El padding vertical ya lo maneja la plantilla,
                //    así que solo dejamos el horizontal para el formulario.
                .padding(horizontal = 16.dp)
        ) {

            // 7. El Text() manual del título SE ELIMINA de aquí

            // El resto de tu formulario se queda exactamente igual
            InfoTextField(
                value = edad,
                onValueChange = { edad = it },
                label = "Edad",
                icon = Icons.Outlined.Person,
                keyboardType = KeyboardType.Number
            )
            InfoTextField(
                value = peso,
                onValueChange = { peso = it },
                label = "Peso (kg)",
                icon = Icons.Outlined.Info,
                keyboardType = KeyboardType.Decimal
            )
            InfoTextField(
                value = altura,
                onValueChange = { altura = it },
                label = "Altura (cm)",
                icon = Icons.Outlined.Info,
                keyboardType = KeyboardType.Number
            )

            Spacer(Modifier.height(24.dp))

            Text("Tipo de sangre", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                bloodTypes.forEach { type ->
                    BloodTypeChip(
                        text = type,
                        isSelected = selectedBloodType == type,
                        onSelected = { selectedBloodType = type }
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            SwitchRowWithTextField(
                label = "Alergias",
                icon = Icons.Outlined.Warning,
                checked = hasAlergias,
                onCheckedChange = { hasAlergias = it },
                details = alergiasDetails,
                onDetailsChange = { alergiasDetails = it }
            )

            SwitchRowWithTextField(
                label = "Enfermedades crónicas",
                icon = Icons.Outlined.FavoriteBorder,
                checked = hasEnfermedades,
                onCheckedChange = { hasEnfermedades = it },
                details = enfermedadesDetails,
                onDetailsChange = { enfermedadesDetails = it }
            )

            SwitchRowWithTextField(
                label = "Medicamentos",
                icon = Icons.Outlined.Warning,
                checked = hasMedicamentos,
                onCheckedChange = { hasMedicamentos = it },
                details = medicamentosDetails,
                onDetailsChange = { medicamentosDetails = it }
            )

            InfoTextField(
                value = nss,
                onValueChange = { nss = it },
                label = "NSS Ingresa tu NSS",
                icon = Icons.Outlined.Lock,
                keyboardType = KeyboardType.Number
            )

            SwitchRow(
                label = "Seguro de Gastos Mayores",
                icon = Icons.Outlined.Edit,
                checked = hasSeguro,
                onCheckedChange = { hasSeguro = it }
            )

            SwitchRowWithTextField(
                label = "Cirugías",
                icon = Icons.Outlined.Info,
                checked = hasCirugias,
                onCheckedChange = { hasCirugias = it },
                details = cirugiasDetails,
                onDetailsChange = { cirugiasDetails = it }
            )

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = { /* TODO: Lógica para guardar datos */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3F51B5))
            ) {
                Text("Guardar", fontSize = 16.sp)
            }
            Spacer(Modifier.height(12.dp))
            Button(
                onClick = {
                    navController.navigate("QR")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
            ) {
                Text("Generar QR para médico", fontSize = 16.sp)
            }

            // Agregamos un Spacer al final para que el último botón
            // no quede pegado al BottomBar al hacer scroll
            Spacer(Modifier.height(16.dp))
        }
    }
}

// ------------------------------------------------------------------
// --- TUS COMPOSABLES PRIVADOS (SIN CAMBIOS) ---
// ------------------------------------------------------------------

@Composable
private fun InfoTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        leadingIcon = { Icon(icon, contentDescription = label) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        singleLine = true
    )
    Spacer(Modifier.height(12.dp))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BloodTypeChip(
    text: String,
    isSelected: Boolean,
    onSelected: () -> Unit
) {
    FilterChip(
        selected = isSelected,
        onClick = onSelected,
        label = { Text(text) },
        shape = CircleShape,
        modifier = Modifier.padding(horizontal = 2.dp)
    )
}

@Composable
private fun SwitchRow(
    label: String,
    icon: ImageVector,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = label, modifier = Modifier.padding(end = 8.dp))
        Text(label, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.weight(1f))
        Switch(checked = checked, onCheckedChange = onCheckedChange)
    }
    Spacer(Modifier.height(12.dp))
}

@Composable
private fun SwitchRowWithTextField(
    label: String,
    icon: ImageVector,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    details: String,
    onDetailsChange: (String) -> Unit
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = label, modifier = Modifier.padding(end = 8.dp))
            Text(label, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.weight(1f))
            Switch(checked = checked, onCheckedChange = onCheckedChange)
        }
        OutlinedTextField(
            value = details,
            onValueChange = onDetailsChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            placeholder = { Text("Detalles...") },
            enabled = checked
        )
    }
    Spacer(Modifier.height(12.dp,))
}
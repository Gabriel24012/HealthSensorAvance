package com.example.healthsensoravance.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.healthsensoravance.ui.theme.DarkText
import com.example.healthsensoravance.ui.theme.PrimaryBlue

@Composable
fun SettingItem(label: String, icon: ImageVector, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = label, tint = PrimaryBlue, modifier = Modifier.size(24.dp).padding(end = 16.dp))
        Text(label, modifier = Modifier.weight(1f), color = DarkText)
        Icon(Icons.Default.Build, contentDescription = "Ir", tint = DarkText.copy(alpha = 0.5f)) // El que elegiste
    }
}
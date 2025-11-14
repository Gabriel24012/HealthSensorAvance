package com.example.healthsensoravance.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Opacity
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material.icons.filled.SsidChart
import androidx.compose.material.icons.filled.WarningAmber
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.healthsensoravance.Routes
import com.example.healthsensoravance.components.BaseContentScreen
import com.example.healthsensoravance.ui.theme.DarkText
import com.example.healthsensoravance.ui.theme.PrimaryBlue

@Composable
fun PronosticoScreen(navController: NavHostController, paddingValues: PaddingValues) {
    BaseContentScreen(title = "Pronóstico de Salud", paddingValues = paddingValues) {
        val configuration = LocalConfiguration.current
        val isLandscape = configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE

        val columns = if (isLandscape) 2 else 1

        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item(span = { GridItemSpan(columns) }) {
                TopSection()
            }

            item(span = { GridItemSpan(columns) }) {
                HealthStatusCard(navController)
            }

            item(span = { GridItemSpan(columns) }) {
                VitalSignsCard()
            }

            item(span = { GridItemSpan(columns) }) {
                Text(
                    "Métricas Vitales (Últimas 24h)",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = DarkText,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }

            items(10) { index ->
                MetricCard(index = index)
            }
        }
    }
}

@Composable
private fun TopSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Dado los ultimos datos recividos hemos de darte a conocer que la precion esta en un punto por encima del promedio, el oxigeno en tu sangre se encuentra deficiente y tu pulso esta en un punto por debajo del promedio",
            color = DarkText.copy(alpha = 0.8f),
            fontSize = 14.sp,
            modifier = Modifier.weight(1f)
        )

    }
}

@Composable
private fun HealthStatusCard(navController: NavHostController) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Estado de Salud",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = DarkText
                )
                IconButton(
                    onClick = { navController.navigate(Routes.RECORDATORIOS) }
                ) {
                    Icon(
                        imageVector = Icons.Filled.CalendarToday,
                        contentDescription = "Calendario",
                        tint = Color(0xFF3F51B5)
                    )
                }

            }
            Spacer(modifier = Modifier.height(24.dp))
            LineGraphComposable()
        }
    }
}

@Composable
private fun LineGraphComposable() {
    val points = listOf(55f, 25f, 28f, 70f)
    val xLabels = listOf("Ene", "Feb", "Marzo", "Abril")

    Column {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        ) {
            val (width, height) = size
            val path = Path()
            val yMin = 0f
            val yMax = 100f

            val xStep = width / (points.size - 1)
            val yToPixel = { y: Float -> height - ((y - yMin) / (yMax - yMin)) * height }

            val dashEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
            (0..2).forEach { i ->
                val y = height * (i / 2f)
                if (i != 2) {
                    drawLine(
                        color = Color.LightGray,
                        start = Offset(0f, y),
                        end = Offset(width, y),
                        strokeWidth = 1f,
                        pathEffect = dashEffect
                    )
                }
            }
            drawLine(
                color = Color.Gray,
                start = Offset(0f, height),
                end = Offset(width, height),
                strokeWidth = 1.5f
            )

            points.forEachIndexed { index, y ->
                val x = index * xStep
                val yPx = yToPixel(y)
                if (index == 0) {
                    path.moveTo(x, yPx)
                } else {
                    path.lineTo(x, yPx)
                }
            }

            drawPath(
                path = path,
                color = PrimaryBlue,
                style = Stroke(width = 5f)
            )

            points.forEachIndexed { index, y ->
                val x = index * xStep
                val yPx = yToPixel(y)
                drawCircle(
                    color = PrimaryBlue,
                    radius = 8f,
                    center = Offset(x, yPx)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            xLabels.forEach { label ->
                Text(
                    text = label,
                    color = DarkText.copy(alpha = 0.7f),
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )
            }
        }
        Text(
            "Tiempo",
            color = DarkText.copy(alpha = 0.7f),
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(top = 4.dp)
        )
    }
}

@Composable
private fun VitalSignsCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = PrimaryBlue)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.WarningAmber,
                    contentDescription = "Peligro",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    "Signos vitales en peligro",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            VitalSignItem(
                icon = Icons.Filled.ShowChart,
                text = "Presión arterial con tendencia a subir"
            )
            VitalSignItem(
                icon = Icons.Filled.SsidChart,
                text = "Signos cardiacos con propensión a fallar"
            )
            VitalSignItem(
                icon = Icons.Filled.Opacity,
                text = "Oxígeno en la sangre se mantiene"
            )
        }
    }
}

@Composable
private fun VitalSignItem(icon: ImageVector, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 6.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White.copy(alpha = 0.9f)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = text,
            color = Color.White,
            fontSize = 14.sp
        )
    }
}

@Composable
private fun MetricCard(index: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = PrimaryBlue.copy(alpha = 0.1f))
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                "Métrica Vital #${index + 1}",
                fontWeight = FontWeight.Bold,
                color = DarkText,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Valor: 75 bpm. Estado: Normal.",
                color = DarkText.copy(alpha = 0.7f),
                fontSize = 14.sp
            )
        }
    }
}
package com.example.healthsensoravance.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val DarkColorScheme = darkColorScheme(
    primary = PrimaryBlue,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryBlue,
    onPrimary = Color.White,
    background = BackgroundWhite,
    surface = BackgroundWhite,
    onBackground = DarkText,
    onSurface = DarkText

)

@Composable
fun HealthSensorTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme

    val appTypography = androidx.compose.material3.Typography()

    MaterialTheme(
        colorScheme = colorScheme,
        typography = appTypography,
        content = content
    )
}
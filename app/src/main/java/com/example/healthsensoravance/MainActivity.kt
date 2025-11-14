package com.example.healthsensoravance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.Surface
import com.example.healthsensoravance.screens.LoginComponent
import com.example.healthsensoravance.ui.theme.HealthSensorTheme

@Preview(showBackground = true)
@Composable
fun PreviewHealthSensorApp() {
    HealthSensorTheme {
        LoginComponent(
            navController = rememberNavController(),
            onLoginSuccess = {}
        )
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HealthSensorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HealthSensorApp()
                }
            }
        }
    }
}
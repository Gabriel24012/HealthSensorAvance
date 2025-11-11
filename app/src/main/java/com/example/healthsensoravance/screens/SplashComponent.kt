package com.example.healthsensoravance.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.healthsensoravance.ui.theme.BackgroundWhite
import com.example.healthsensoravance.ui.theme.DarkText
import com.example.healthsensoravance.ui.theme.PrimaryBlue
import com.example.healthsensoravance.viewmodels.SplashViewModel


@Composable
fun SplashComponent(
    onTimeout: () -> Unit,
    viewModel: SplashViewModel = viewModel()
) {
    LaunchedEffect(key1 = viewModel.isLoading.value) {
        if (!viewModel.isLoading.value) {
            onTimeout()
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundWhite)
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Default.Favorite,
                contentDescription = "Logo Health Sensor",
                modifier = Modifier.size(150.dp),
                tint = PrimaryBlue
            )
            Text(
                text = "HEALTH SENSOR",
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 50.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = DarkText
            )
        }
    }
}
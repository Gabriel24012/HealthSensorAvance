package com.example.healthsensoravance.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

// No usaremos BaseContentScreen aquí para tener control total del diseño
@Composable
fun QR(navController: NavHostController) {

    // Usamos Scaffold para un fondo consistente
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFF0F2F5) // Un color de fondo gris claro como en la imagen
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp) // Margen exterior para la tarjeta
                .verticalScroll(rememberScrollState()), // Permite scroll si el contenido es muy largo
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Tarjeta principal que contiene todo
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // --- SECCIÓN SUPERIOR AZUL ---
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Color(0xFF3F51B5), // Color azul similar al de la imagen
                                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                            )
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "QR para tu médico",
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Muestra este código QR a tu médico para compartir tu ficha médica",
                            color = Color.White,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        // Placeholder para el Código QR
                        Card(
                            modifier = Modifier.size(250.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "[Código QR]",
                                    color = Color.Gray,
                                    fontSize = 20.sp
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // Botón "Volver a ficha médica"
                        Button(
                            onClick = { navController.popBackStack() }, // Acción para volver atrás
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = "Volver a ficha médica",
                                color = Color(0xFF3F51B5), // Color del texto azul
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
                    }

                    // --- SECCIÓN INFERIOR BLANCA ---
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                    ) {
                        Text(
                            text = "Resumen de Ficha Médica",
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Tarjeta para el resumen
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            border = BorderStroke(1.dp, Color(0xFFE0E0E0)) // Borde sutil
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp) // Espacio entre textos
                            ) {
                                Text(text = "Edad: ...", color = Color.Black, fontSize = 14.sp)
                                Text(text = "Alergias: ...", color = Color.Black, fontSize = 14.sp)
                                Text(text = "Enfermedades: ...", color = Color.Black, fontSize = 14.sp)
                                Text(text = "Seguro: ...", color = Color.Black, fontSize = 14.sp)
                                Text(text = "Cirugías: ...", color = Color.Black, fontSize = 14.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}
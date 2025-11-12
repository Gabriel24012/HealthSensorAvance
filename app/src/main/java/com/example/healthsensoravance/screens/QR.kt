package com.example.healthsensoravance.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.healthsensoravance.components.BaseContentScreen

@Composable
fun QR(navController: NavHostController) {
    BaseContentScreen(title = "QR Para Tu Medico", showBack = true, navController = navController) {
        Text("Aqui esta tu QR para que se lo compartas a tu medico", Modifier.padding(16.dp))
    }
}

//estoy haciendo lo de la app mobile en android studio, quiero hacer solo la vista para el QR ,para que el usuario pueda crear su QR de la ficha medica, este es el codigo que tengo de QR dentro de la carpeta screens:  package com.example.healthsensoravance.screens
//
//
//
//import androidx.compose.foundation.layout.padding
//
//import androidx.compose.material3.Text
//
//import androidx.compose.runtime.Composable
//
//import androidx.compose.ui.Modifier
//
//import androidx.compose.ui.unit.dp
//
//import androidx.navigation.NavHostController
//
//import com.example.healthsensoravance.components.BaseContentScreen
//
//
//
//@Composable
//
//fun QR(navController: NavHostController) {
//
//    BaseContentScreen(title = "QR Para Tu Medico", showBack = true, navController = navController) {
//
//        Text("Aqui esta tu QR para que se lo compartas a tu medico", Modifier.padding(16.dp))
//
//    }
//
//}         mandame el codigo solo para la vista y que sea como el de la imagen que te voy a mandar:
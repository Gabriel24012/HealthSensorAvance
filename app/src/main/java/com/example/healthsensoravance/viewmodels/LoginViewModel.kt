package com.example.healthsensoravance.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var errorMessage by mutableStateOf<String?>(null)

    fun onLoginClicked(onSuccess: () -> Unit) {
        if (email.isBlank() || password.isBlank()) {
            errorMessage = "Por favor, completa todos los campos."
            return
        }
        if (email == "a" && password == "a") {
            errorMessage = null
            onSuccess()
        } else {
            errorMessage = "Credenciales incorrectas."
        }
    }
}

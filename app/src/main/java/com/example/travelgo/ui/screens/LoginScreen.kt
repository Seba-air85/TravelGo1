package com.example.travelgo.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }
    var passError by remember { mutableStateOf(false) }

    // Estado para disparar navegación
    var navigateToPackages by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            isError = emailError
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            isError = passError,
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                emailError = !email.contains("@")
                passError = password.length < 6

                if (!emailError && !passError) {
                    navigateToPackages = true
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ingresar")
        }
    }

    // La navegación se ejecuta desde un Composable válido
    if (navigateToPackages) {
        LaunchedEffect(Unit) {
            navController.navigate("paquetes")
            navigateToPackages = false
        }
    }
}

package com.example.travelgo.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.travelgo.ui.screens.*

@Composable
fun AppNav() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "home") {

        composable("home") { HomeScreen(navController) }
        composable("paquetes") { PaquetesScreen(navController) }
        composable("cliente") { ClienteScreen(navController) }
        composable("perfil") { PerfilScreen(navController) }
    }
}
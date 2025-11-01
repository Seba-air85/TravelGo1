package com.example.travelgo.Navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.travelgo.ClienteScreen
import com.example.travelgo.PerfilScreen
import com.example.travelgo.ui.screens.ClienteScreen
import com.example.travelgo.ui.screens.HomeScreen
import com.example.travelgo.ui.screens.PaquetesScreen
import com.example.travelgo.ui.screens.PerfilScreen
import com.example.travelgo.ui.screens.LoginScreen

@Composable
fun AppNav() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "login",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("login") { LoginScreen(navController) }
            composable("home") { HomeScreen(navController) }
            composable("paquetes") { PaquetesScreen(navController) }
            composable("cliente") { ClienteScreen(navController) }
            composable("perfil") { PerfilScreen(navController) }
        }
    }
}

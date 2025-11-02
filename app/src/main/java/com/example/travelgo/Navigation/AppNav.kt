package com.example.travelgo

import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.travelgo.ui.theme.TravelGoTheme
import com.example.travelgo.ui.components.BottomBar
import com.example.travelgo.ui.screens.ClienteScreen
import com.example.travelgo.ui.screens.DetailsScreen
import com.example.travelgo.ui.screens.HomeScreen
import com.example.travelgo.ui.screens.LoginScreen
import com.example.travelgo.ui.screens.PaquetesScreen
import com.example.travelgo.ui.screens.ProfileScreen

@Composable
fun AppNav(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("details") { DetailsScreen(navController) }
        composable("paquetes") { PaquetesScreen(navController) }
        composable("cliente") { ClienteScreen(navController) }
        composable("perfil") { PerfilScreen(navController) }
        composable("favorites") { FavoritesScreen(navController) }
        composable("profile") { ProfileScreen(navController) }
        composable("login") { LoginScreen(navController) }
    }
}

// Pantallas placeholders
@Composable
fun HomeScreen(navController: NavHostController) {
    Button(onClick = { navController.navigate("details") }) {
        Text("Ir a detalles")
    }
}

@Composable
fun DetailsScreen(navController: NavHostController) {
    Text("Pantalla de detalles")
}

@Composable
fun PaquetesScreen(navController: NavHostController) {
    Text("Paquetes")
}

@Composable
fun ClienteScreen(navController: NavHostController) {
    Text("Cliente")
}

@Composable
fun PerfilScreen(navController: NavHostController) {
    Text("Perfil")
}

@Composable
fun FavoritesScreen(navController: NavHostController) {
    Text("Favoritos")
}

@Composable
fun ProfileScreen(navController: NavHostController) {
    Text("Mi perfil")
}

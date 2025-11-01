package com.example.travelgo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.travelgo.ui.theme.TravelGoTheme
import com.example.travelgo.ui.screens.DetailsScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.travelgo.ui.screens.HomeScreen
import androidx.navigation.NavController
import androidx.compose.material3.Button
import com.example.travelgo.ui.screens.PaquetesScreen
import com.example.travelgo.ui.screens.ClienteScreen
import com.example.travelgo.ui.screens.PerfilScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TravelGoTheme {
                AppNav()
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        Greeting(
                            name = "Android",
                                modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
@Composable
fun AppContent() {
    HomeScreen(navController)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TravelGoTheme {
        Greeting("Android")
    }
}

@Composable
fun AppNav() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("details") { DetailsScreen(navController) }
        composable("paquetes") { PaquetesScreen(navController) }
        composable("cliente") { ClienteScreen(navController) }
        composable("perfil") { PerfilScreen(navController) }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    // ejemplo de navegación
    Button(onClick = { navController.navigate("details") }) {
        Text("Ir a detalles")
    }
}

@Composable
fun DetailsScreen(navController: NavController) {
    Text("Pantalla de detalles")
}
@Composable
fun PaquetesScreen(navController: NavController) { Text("Paquetes") }
@Composable
fun ClienteScreen(navController: NavController) { Text("Cliente") }
@Composable
fun PerfilScreen(navController: NavController) { Text("Perfil") }
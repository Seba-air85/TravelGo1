package com.example.travelgo.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.travelgo.Navigation.NavItem
import androidx.compose.material.icons.filled.FlightTakeoff
import androidx.compose.material.icons.filled.Place

private val Icons.Filled.Flight: ImageVector
    get() = Icons.Filled.Place


@Composable
fun BottomBar(navController: NavController) {
    val items = listOf(
        NavItem("home", "Inicio", Icons.Filled.Home),
        NavItem("paquetes", "Paquetes", Icons.Filled.Flight),
        NavItem("cliente", "Cliente", Icons.Filled.Person),
        NavItem("perfil", "Perfil", Icons.Filled.Settings)
    )


    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = { navController.navigate(item.route) },
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) }
            )
        }
    }
}
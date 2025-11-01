package com.example.travelgo.Navigation

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.travelgo.ui.screens.*

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.List

@Composable
fun BottomBar(navController: NavController) {
    val items = listOf(
        BottomNavItem("paquetes", Icons.Default.List, "Paquetes"),
        BottomNavItem("cliente", Icons.Default.Home, "Cliente"),
        BottomNavItem("perfil", Icons.Default.Person, "Perfil")
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
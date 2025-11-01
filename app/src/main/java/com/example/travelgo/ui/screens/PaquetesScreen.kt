package com.example.travelgo.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.travelgo.viewmodel.PackagesViewModel

@Composable
fun PaquetesScreen(navController: NavController, vm: PackagesViewModel = viewModel()) {

    val packages by vm.packages.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        items(packages) { pkg ->
            Card(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                Row(modifier = Modifier.padding(12.dp)) {
                    Image(
                        painter = rememberAsyncImagePainter(pkg.imageUrl),
                        contentDescription = null,
                        modifier = Modifier.size(80.dp)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(pkg.name, fontWeight = FontWeight.Bold)
                        Text(pkg.category)
                        Text("$${pkg.price} USD")

                        Spacer(modifier = Modifier.height(6.dp))

                        Button(
                            onClick = { /* TODO: add to cart global state */ }
                        ) { Text("Agregar") }
                    }
                }
            }
        }
    }
}
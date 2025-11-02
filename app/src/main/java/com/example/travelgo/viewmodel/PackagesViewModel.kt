package com.example.travelgo.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.travelgo.data.travelMock
import com.example.travelgo.model.TravelPackage

class PackagesViewModel : ViewModel() {
    private val _packages = MutableStateFlow(
        listOf(
            Package("Tour París", "Vuelo + Hotel", 1200, "https://via.placeholder.com/150"),
            Package("Tour Roma", "Vuelo + Hotel", 1000, "https://via.placeholder.com/150")
        )
    )
    val packages: StateFlow<List<Package>> = _packages
}

data class Package(
    val name: String,
    val category: String,
    val price: Int,
    val imageUrl: String
)
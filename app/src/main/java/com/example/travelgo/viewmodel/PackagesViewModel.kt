package com.example.travelgo.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.travelgo.data.travelMock
import com.example.travelgo.model.TravelPackage

class PackagesViewModel : ViewModel() {

    private val _packages = MutableStateFlow<List<TravelPackage>>(emptyList())
    val packages: StateFlow<List<TravelPackage>> = _packages

    init {
        _packages.value = travelMock
    }
}
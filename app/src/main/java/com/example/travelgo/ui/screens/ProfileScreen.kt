package com.example.travelgo.ui.screens

import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.travelgo.data.ApiService
import com.example.travelgo.data.UserPrefs
import com.example.travelgo.model.User
import com.example.travelgo.ui.UiState
import kotlinx.coroutines.launch
import androidx.compose.ui.Alignment
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import com.example.travelgo.ui.components.ErrorBox
import com.example.travelgo.ui.components.LoadingBox
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import retrofit2.Response.success
import java.io.File
import androidx.compose.foundation.Image
import coil.compose.rememberAsyncImagePainter
@Composable
private fun ProfileContent(user: User, prefs: UserPrefs) {
    // State para la URI de la imagen
    val avatarUri = remember { mutableStateOf(prefs.getProfileImageUri()) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        avatarUri.value?.let { uriString ->
            val uri = Uri.parse(uriString)
            Image(
                painter = rememberAsyncImagePainter(uri),
                contentDescription = "Profile Image",
                modifier = Modifier.size(128.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Picker para cambiar imagen
        ProfileImagePicker { selectedUri ->
            prefs.saveProfileImageUri(selectedUri.toString()) // guardar en preferencias
            avatarUri.value = selectedUri.toString()          // actualizar UI inmediatamente
        }
    }
}

@Composable
fun ProfileScreen(
    navController: androidx.navigation.NavController,
    api: ApiService,
    prefs: UserPrefs,
    modifier: Modifier = Modifier
) {
    var uiState by remember { mutableStateOf<UiState<User>>(UiState.Idle) }
    val context = LocalContext.current

    // Cargar usuario cacheado si existe
    prefs.getUser()?.let { cached ->
        uiState = UiState.Success(cached)
    }

    // Traer /me del backend
    LaunchedEffect(Unit) {
        uiState = UiState.Loading
        try {
            val token = prefs.getToken()
            val user = if (token != null) {
                api.getMe() // tu API devuelve User
            } else {
                prefs.getUser() ?: throw IllegalStateException("No token and no cached user")
            }

            prefs.saveUser(user) // <-- persistencia local
            uiState = UiState.Success(user)
        } catch (e: Exception) {
            uiState = UiState.Error(e)
        }
    }

    when (val s = uiState) {
        is UiState.Loading -> LoadingBox()
        is UiState.Error -> {
            val message = s.throwable.message ?: "Error al obtener perfil"
            ErrorBox(message = message) {
                uiState = UiState.Loading
            }
        }
        is UiState.Success -> {
            ProfileContent(user = s.data, prefs = prefs)
        }
        UiState.Idle -> {}
    }
}

@Composable
fun ProfileImagePicker(onImageSelected: (Uri) -> Unit) {
    val context = LocalContext.current
    val cameraImageUriState = remember { mutableStateOf<Uri?>(null) }

    // Launcher galería
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { onImageSelected(it) }
    }

    // Launcher cámara
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success: Boolean ->
        if (success) {
            cameraImageUriState.value?.let { uri ->
                onImageSelected(uri)
            }
        }
    }

    Column {
        Button(onClick = {
            galleryLauncher.launch("image/*")
        }) {
            Text("Seleccionar desde galería")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            val file = File(context.cacheDir, "profile_image_${System.currentTimeMillis()}.jpg")
            cameraImageUriState.value = FileProvider.getUriForFile(
                context,
                "${context.packageName}.provider",
                file
            )
            cameraImageUriState.value?.let { uri ->
                cameraLauncher.launch(uri)
            }
        }) {
            Text("Tomar foto con cámara")
        }
    }
}


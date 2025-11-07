package com.example.lab12_maps

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun MapScreen() {
    // 🌎 Coordenadas iniciales: Arequipa
    val arequipa = LatLng(-16.3989, -71.5350)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(arequipa, 12f)
    }

    // 🔹 Estado del tipo de mapa
    var mapType by remember { mutableStateOf(MapType.NORMAL) }

    Column(modifier = Modifier.fillMaxSize()) {
        // 🗺️ Mapa
        GoogleMap(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(mapType = mapType),
            uiSettings = MapUiSettings(zoomControlsEnabled = true)
        ) {
            // 📍 Marcador de Arequipa
            Marker(
                state = rememberMarkerState(position = arequipa),
                title = "Arequipa, Perú",
                snippet = "Ciudad Blanca"
            )
        }

        // 🔘 Botones para cambiar el tipo de mapa
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { mapType = MapType.NORMAL }) { Text("Normal") }
            Button(onClick = { mapType = MapType.HYBRID }) { Text("Hybrid") }
            Button(onClick = { mapType = MapType.TERRAIN }) { Text("Terrain") }
            Button(onClick = { mapType = MapType.SATELLITE }) { Text("Satellite") }
        }
    }
}

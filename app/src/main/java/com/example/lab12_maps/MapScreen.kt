package com.example.lab12_maps

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.Polygon
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import kotlinx.coroutines.delay

@Composable
fun MapScreen() {
    val context = LocalContext.current

    // 📍 Coordenadas base: Arequipa
    val arequipa = LatLng(-16.3989, -71.5350)

    // 📸 Control de cámara
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(arequipa, 12f)
    }

    // 🌍 Varias ubicaciones
    val locations = listOf(
        LatLng(-16.433415, -71.5442652), // JLByR
        LatLng(-16.4205151, -71.4945209), // Paucarpata
        LatLng(-16.3524187, -71.5675994), // Zamacola
        arequipa // Arequipa también
    )

    // 🗺️ Polígonos (he aumentado un poco las coordenadas para que se vean más grandes)
    val mallAventuraPolygon = listOf(
        LatLng(-16.4330, -71.5100),
        LatLng(-16.4335, -71.5095),
        LatLng(-16.4338, -71.5092),
        LatLng(-16.4332, -71.5087)
    )

    val parqueLambramaniPolygon = listOf(
        LatLng(-16.4220, -71.5310),
        LatLng(-16.4230, -71.5315),
        LatLng(-16.4235, -71.5312),
        LatLng(-16.4225, -71.5305)
    )

    val plazaDeArmasPolygon = listOf(
        LatLng(-16.3980, -71.5375),
        LatLng(-16.3985, -71.5365),
        LatLng(-16.3995, -71.5360),
        LatLng(-16.3990, -71.5370)
    )

    // 🗺️ Mapa
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        // 📦 Icono personalizado escalado (más pequeño)
        val customIcon: BitmapDescriptor = remember {
            val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.img_1)
            val smallBitmap = Bitmap.createScaledBitmap(bitmap, 60, 60, false) // tamaño reducido
            BitmapDescriptorFactory.fromBitmap(smallBitmap)
        }

        // 📍 Marcadores en todas las ubicaciones
        locations.forEach { location ->
            Marker(
                state = rememberMarkerState(position = location),
                icon = customIcon,
                title = "Ubicación",
                snippet = "Punto de interés"
            )
        }

        // 🔷 Dibujar polígonos con mayor visibilidad
        Polygon(
            points = plazaDeArmasPolygon,
            strokeColor = Color.Red,
            fillColor = Color.Red.copy(alpha = 0.4f), // más visible
            strokeWidth = 8f
        )
        Polygon(
            points = parqueLambramaniPolygon,
            strokeColor = Color.Magenta,
            fillColor = Color.Magenta.copy(alpha = 0.4f),
            strokeWidth = 8f
        )
        Polygon(
            points = mallAventuraPolygon,
            strokeColor = Color.Yellow,
            fillColor = Color.Yellow.copy(alpha = 0.4f),
            strokeWidth = 8f
        )
    }

    // 🎥 Mover cámara automáticamente hacia Yura
    LaunchedEffect(Unit) {
        delay(2000) // Espera un momento antes de mover
        cameraPositionState.animate(
            update = CameraUpdateFactory.newLatLngZoom(
                LatLng(-16.2520984, -71.6836503), // 📍 Yura
                12f
            ),
            durationMs = 3000
        )
    }
}

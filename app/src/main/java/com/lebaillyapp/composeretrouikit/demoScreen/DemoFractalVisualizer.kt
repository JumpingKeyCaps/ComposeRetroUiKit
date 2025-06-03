package com.lebaillyapp.composeretrouikit.demoScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lebaillyapp.composeretrouikit.visualizer.FractalVisualizer
/**
 *
 * ### Demo_FractalVisualizer
 *
 * Composable de démonstration affichant un visualiseur fractal interactif avec des paramètres ajustables via sliders.
 *
 * Cette UI permet de modifier en temps réel plusieurs paramètres du visualiseur fractal :
 * - `pointCount` : nombre de points utilisés pour dessiner la fractale,
 * - `maxRadius` : rayon maximal (en dp) de la spirale fractale,
 * - `spiralFrequency` : fréquence de la spirale,
 * - `internalOscillationFreq` : fréquence d'oscillation interne,
 * - `rotationSpeed` : vitesse de rotation de l'animation.
 *
 * Chaque paramètre est contrôlé par un slider avec une couleur personnalisée pour une meilleure intégration visuelle.
 *
 * Le composable utilise un `Box` avec fond noir et centre le contenu, une colonne contenant le visualiseur et les sliders
 * encapsulés dans une carte stylisée.
 *
 * Utilise le composable `FractalVisualizer` pour l'affichage graphique principal.
 */
@Composable
fun Demo_FractalVisualizer(demoColor: Color) {

    var pointCount by remember { mutableIntStateOf(80) }
    var maxRadius by remember { mutableFloatStateOf(40f) } // en dp
    var spiralFrequency by remember { mutableFloatStateOf(8f) }
    var internalOscillationFreq by remember { mutableFloatStateOf(12f) }
    var rotationSpeed by remember { mutableFloatStateOf(1.5f) }
    val animationDurationMs by remember { mutableIntStateOf(60000) }


    val globalColor by remember { mutableStateOf(Color(0xFF282525)) }
    val textGlobalColor = Color.DarkGray

    val sliderColors = SliderDefaults.colors(
        thumbColor = globalColor,
        activeTrackColor = globalColor.copy(alpha = 0.7f),
        inactiveTrackColor = globalColor.copy(alpha = 0.2f),
        activeTickColor = globalColor,
        inactiveTickColor = globalColor.copy(alpha = 0.3f)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(top = 170.dp),
            contentAlignment = Alignment.Center
        ) {
            FractalVisualizer(
                modifier = Modifier.size(300.dp),
                glowColor = demoColor,
                maxRadius = maxRadius.dp,
                pointCount = pointCount,
                spiralFrequency = spiralFrequency,
                internalOscillationFreq = internalOscillationFreq,
                rotationSpeed = rotationSpeed,
                animationDurationMs = animationDurationMs,
                isAnimating = true
            )
        }


        Card(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .wrapContentHeight()
                .align(Alignment.BottomCenter)
                .padding(bottom = 36.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF111010)
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            )
        ) {
            Column(
                modifier = Modifier.padding(18.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val textStyle = TextStyle(color = Color.White, fontSize = 12.sp)
                val sliderModifier = Modifier
                    .fillMaxWidth()
                    .height(28.dp)

                Text("Point Count: $pointCount", style = textStyle,color = textGlobalColor)
                Slider(
                    value = pointCount.toFloat(),
                    onValueChange = { pointCount = it.toInt() },
                    valueRange = 10f..1500f,
                    modifier = sliderModifier,
                    colors = sliderColors
                )

                Text("Max Radius: ${maxRadius.toInt()} dp", style = textStyle,color = textGlobalColor)
                Slider(
                    value = maxRadius,
                    onValueChange = { maxRadius = it },
                    valueRange = 10f..500f,
                    modifier = sliderModifier,
                    colors = sliderColors
                )

                Text("Spiral Frequency: ${"%.1f".format(spiralFrequency)}", style = textStyle,color = textGlobalColor)
                Slider(
                    value = spiralFrequency,
                    onValueChange = { spiralFrequency = it },
                    valueRange = 1f..400f,
                    modifier = sliderModifier,
                    colors = sliderColors
                )

                Text("Internal Oscillation Freq: ${"%.1f".format(internalOscillationFreq)}", style = textStyle,color = textGlobalColor)
                Slider(
                    value = internalOscillationFreq,
                    onValueChange = { internalOscillationFreq = it },
                    valueRange = 1f..200f,
                    modifier = sliderModifier,
                    colors = sliderColors
                )

                Text("Rotation Speed: ${"%.2f".format(rotationSpeed)}", style = textStyle,color = textGlobalColor)
                Slider(
                    value = rotationSpeed,
                    onValueChange = { rotationSpeed = it },
                    valueRange = 0f..10f,
                    modifier = sliderModifier,
                    colors = sliderColors
                )
            }
        }
    }
}
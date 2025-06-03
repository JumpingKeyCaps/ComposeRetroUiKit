package com.lebaillyapp.composeretrouikit.visualizer

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import kotlin.math.sin
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
/**
 *
 * ### WaveformVisualizer
 *
 * Affiche un visualiseur d'onde sinusoïdale en temps réel basé sur une fréquence donnée.
 *
 * Cette composable dessine dynamiquement un signal sinusoïdal simulé dans une fenêtre de temps glissante,
 * représenté sur un `Canvas`, avec une mise à jour fluide à environ 60 FPS.
 *
 * Il est utile pour visualiser en direct un signal audio généré à une fréquence constante.
 *
 * @param frequencyHz Fréquence du signal sinusoïdal à visualiser, en hertz (Hz).
 * @param factorVisualizer Facteur de division appliqué à la fréquence pour ralentir visuellement l'onde (par défaut = 1).
 * @param modifier Modificateur Compose pour configurer la taille, le placement, etc.
 * @param visualWindowDurationMs Durée de la fenêtre temporelle affichée, en millisecondes (ex : 50 ms).
 * @param visualResolutionPointsPerMs Résolution visuelle, en nombre de points dessinés par milliseconde (ex : 50 points/ms).
 * @param amplitude Amplitude du signal, comprise entre 0f (silencieux) et 1f (max visuel).
 * @param waveformColor Couleur du tracé de l'onde sinusoïdale.
 */
@Composable
fun WaveformVisualizer(
    modifier: Modifier = Modifier,
    frequencyHz: Float,
    factorVisualizer: Float = 1f,
    visualWindowDurationMs: Float = 50f,
    visualResolutionPointsPerMs: Float = 50f,
    amplitude: Float = 0.8f,
    waveformColor: Color = Color.Cyan
) {
    val visualWindowDurationSeconds = visualWindowDurationMs / 1000f
    val totalDisplayPoints = (visualWindowDurationMs * visualResolutionPointsPerMs).toInt().coerceAtLeast(2)

    // Cet état déclenche le recomposé à chaque frame
    var currentTimeSec by remember { mutableStateOf(0f) }

    // Animation "cadencée" à 60 FPS environ, déclenche un recomposé
    LaunchedEffect(Unit) {
        val startTimeNs = System.nanoTime()
        while (true) {
            withFrameNanos { now ->
                val elapsedSec = (now - startTimeNs) / 1_000_000_000f
                currentTimeSec = elapsedSec
            }
        }
    }

    // Calcul dynamique sans remember (car redessiné chaque frame)
    val amplitudes = List(totalDisplayPoints) { i ->
        val time = i.toFloat() / (totalDisplayPoints - 1) * visualWindowDurationSeconds
        val angle = 2 * Math.PI * (frequencyHz/factorVisualizer) * (time + currentTimeSec)
        (amplitude * sin(angle)).toFloat()
    }

    Canvas(modifier = modifier) {
        val width = size.width
        val height = size.height
        val halfHeight = height / 2f

        val path = Path().apply {
            moveTo(0f, halfHeight + amplitudes[0] * halfHeight)
            for (i in 1 until amplitudes.size) {
                val x = i * (width / (amplitudes.size - 1).toFloat())
                val y = halfHeight + amplitudes[i] * halfHeight
                lineTo(x, y)
            }
        }

        drawPath(
            path = path,
            color = waveformColor,
            style = Stroke(width = 2f)
        )
    }
}
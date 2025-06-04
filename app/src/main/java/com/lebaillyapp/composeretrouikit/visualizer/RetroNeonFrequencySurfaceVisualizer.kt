package com.lebaillyapp.composeretrouikit.visualizer

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import kotlin.math.*

/**
 * Composant Compose affichant une grille 3D wireframe avec une déformation circulaire stable représentant
 * une fréquence donnée sous forme d'un pic localisé sur la grille.
 *
 * La grille est dessinée avec projection isométrique simple, et une seule déformation en forme de "montagne"
 * est appliquée, centrée sur la position correspondant à la fréquence passée en paramètre.
 *
 * Les hautes fréquences sont en bas à droite, les basses en haut à gauche.
 * Le reste de la grille reste plat.
 *
 * @param frequencyHz Fréquence en Hertz à visualiser (entre 1 et 20000)
 * @param sigma Écart-type (standard deviation) de la gaussienne contrôlant la largeur du pic (plus petit = pic plus serré)
 * @param peakHeight Hauteur maximale du pic (amplitude)
 * @param gridCols Nombre de colonnes (points horizontaux) de la grille
 * @param gridRows Nombre de rangées (points verticaux) de la grille
 * @param cellSize Taille d'une case de la grille en pixels
 * @param modifier Modifier Compose standard pour taille et positionnement
 */
@Composable
fun RetroNeonFrequencySurface(
    modifier: Modifier = Modifier,
    frequencyHz: Float,
    sigma: Float = 3.5f,
    peakHeight: Float = 20f,
    gridCols: Int = 40,
    gridRows: Int = 20,
    cellSize: Float = 20f,
    heatMapColor1: Color = Color.Cyan,
    heatMapColor2: Color = Color.Magenta,
    heatMapColor3: Color = Color.Red,

    ) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // Projection isométrique 3D -> 2D avec cellSize (taille de case)
        fun project3D(x: Float, y: Float, z: Float): Offset {
            // Conversion grille 2D + hauteur z en coordonnées iso
            val isoX = (x - y) * cos(PI / 6).toFloat() * cellSize
            val isoY = (x + y) * sin(PI / 6).toFloat() * cellSize - z
            // Centre la grille dans le canvas
            return Offset(
                isoX + w / 2,
                isoY + h / 2
            )
        }

        val freqClamped = frequencyHz.coerceIn(1f, 20000f)

        val freqRatio = (freqClamped - 1f) / (20000f - 1f)
        val peakCol = freqRatio * gridCols
        val peakRow = freqRatio * gridRows

        val points = Array(gridRows + 1) { row ->
            FloatArray(gridCols + 1) { col ->
                val dx = col - peakCol
                val dy = row - peakRow
                val distSquared = dx * dx + dy * dy

                val z = peakHeight * exp(-distSquared / (2 * sigma * sigma))

                z
            }
        }

        val zMax = points.maxOf { row -> row.maxOrNull() ?: 0f }
        val zMin = points.minOf { row -> row.minOrNull() ?: 0f }
        val zRange = (zMax - zMin).takeIf { it > 0f } ?: 1f

        fun heatmapColor(z: Float): Color {
            val t = (z - zMin) / zRange
            val c1 = heatMapColor1
            val c2 = heatMapColor2
            val c3 = heatMapColor3

            return when {
                t < 0.5f -> lerp(c1, c2, t * 2f)
                else -> lerp(c2, c3, (t - 0.5f) * 2f)
            }
        }

        // Lignes horizontales
        for (row in 0..gridRows) {
            for (col in 0 until gridCols) {
                val z1 = points[row][col]
                val z2 = points[row][col + 1]

                val p1 = project3D(col.toFloat(), row.toFloat(), z1)
                val p2 = project3D(col + 1f, row.toFloat(), z2)

                val color1 = heatmapColor(z1).copy(alpha = 0.8f)
                val color2 = heatmapColor(z2).copy(alpha = 0.8f)

                val brush = Brush.linearGradient(listOf(color1, color2), start = p1, end = p2)

                drawLine(
                    brush = brush,
                    start = p1,
                    end = p2,
                    strokeWidth = 2f,
                    cap = StrokeCap.Round
                )
            }
        }

        // Lignes verticales
        for (col in 0..gridCols) {
            for (row in 0 until gridRows) {
                val z1 = points[row][col]
                val z2 = points[row + 1][col]

                val p1 = project3D(col.toFloat(), row.toFloat(), z1)
                val p2 = project3D(col.toFloat(), row + 1f, z2)

                val color1 = heatmapColor(z1).copy(alpha = 0.8f)
                val color2 = heatmapColor(z2).copy(alpha = 0.8f)

                val brush = Brush.linearGradient(listOf(color1, color2), start = p1, end = p2)

                drawLine(
                    brush = brush,
                    start = p1,
                    end = p2,
                    strokeWidth = 2f,
                    cap = StrokeCap.Round
                )
            }
        }

        // Glow blanc néon
        val glowAlpha = 0.15f
        val glowStroke = 6f

        for (row in 0..gridRows) {
            for (col in 0 until gridCols) {
                val z1 = points[row][col]
                val z2 = points[row][col + 1]

                val p1 = project3D(col.toFloat(), row.toFloat(), z1)
                val p2 = project3D(col + 1f, row.toFloat(), z2)

                drawLine(
                    color = Color.White.copy(alpha = glowAlpha),
                    start = p1,
                    end = p2,
                    strokeWidth = glowStroke,
                    cap = StrokeCap.Round
                )
            }
        }

        for (col in 0..gridCols) {
            for (row in 0 until gridRows) {
                val z1 = points[row][col]
                val z2 = points[row + 1][col]

                val p1 = project3D(col.toFloat(), row.toFloat(), z1)
                val p2 = project3D(col.toFloat(), row + 1f, z2)

                drawLine(
                    color = Color.White.copy(alpha = glowAlpha),
                    start = p1,
                    end = p2,
                    strokeWidth = glowStroke,
                    cap = StrokeCap.Round
                )
            }
        }
    }
}
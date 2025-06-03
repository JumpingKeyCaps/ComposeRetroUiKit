package com.lebaillyapp.composeretrouikit.segmentsDisplay.sevenSeg


import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.sin


/**
 * ### SevenSegmentDigitDisplay
 *
 * Affiche un chiffre (0–9) sur un afficheur à 7 segments dessiné en `Canvas`,
 * avec effets visuels réalistes :
 * - **Segments biseautés** avec `Path`
 * - **Glow flou** autour des segments allumés
 * - **Flicker aléatoire** pour un effet de scintillement léger
 *
 * Idéal pour créer une interface rétro numérique type LED/VFD/Nixie stylisée.
 *
 * @param digit Chiffre à afficher (0–9). Si invalide, tous les segments sont éteints.
 * @param modifier Modificateur pour positionnement et dimensionnement externe.
 * @param segmentLength Longueur d’un segment (horizontal ET vertical).
 * @param segmentThickness Épaisseur d’un segment.
 * @param bevel Taille du chanfrein appliqué aux coins des segments.
 * @param onColor Couleur des segments allumés.
 * @param offColor Couleur des segments éteints (affiché avec transparence réduite).
 * @param alpha Opacité globale des segments (de 0f à 1f).
 * @param glowRadius Rayon du flou (glow) autour des segments allumés, en **pixels**.
 * @param flickerAmplitude Amplitude de scintillement (alpha instable) des segments allumés.
 * @param flickerFrequency Fréquence de scintillement en Hz.
 */
@Composable
fun SevenSegmentDigitDisplay(
    digit: Int,
    modifier: Modifier = Modifier,
    segmentLength: Dp = 80.dp,
    segmentThickness: Dp = 20.dp,
    bevel: Dp = 6.dp,
    onColor: Color = Color.Red,
    offColor: Color = Color.DarkGray,
    alpha: Float = 1f,
    glowRadius: Float = 15f,
    flickerAmplitude: Float = 0.1f,
    flickerFrequency: Float = 3f
) {
    val density = LocalDensity.current
    val segLenPx = with(density) { segmentLength.toPx() }
    val segThkPx = with(density) { segmentThickness.toPx() }
    val bevelPx = with(density) { bevel.toPx() }

    val infiniteTransition = rememberInfiniteTransition()
    val time by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Restart
        )
    )

    // Flicker noise de base aléatoire pour chaque segment (stable)
    val flickerAlpha = remember {
        List(7) { kotlin.random.Random(it).nextFloat() * 0.5f + 0.5f }
    }

    fun flicker(index: Int): Double {
        val base = flickerAlpha.getOrNull(index) ?: 1f
        return alpha * (base + flickerAmplitude * sin(2 * Math.PI * flickerFrequency * time + index))
            .coerceIn(0.0, 1.0)
    }

    fun segmentHorizontal(x: Float, y: Float): Path = Path().apply {
        moveTo(x + bevelPx, y)
        lineTo(x + segLenPx - bevelPx, y)
        lineTo(x + segLenPx, y + bevelPx)
        lineTo(x + segLenPx, y + segThkPx - bevelPx)
        lineTo(x + segLenPx - bevelPx, y + segThkPx)
        lineTo(x + bevelPx, y + segThkPx)
        lineTo(x, y + segThkPx - bevelPx)
        lineTo(x, y + bevelPx)
        close()
    }

    fun segmentVertical(x: Float, y: Float): Path = Path().apply {
        moveTo(x, y + bevelPx)
        lineTo(x + bevelPx, y)
        lineTo(x + segThkPx - bevelPx, y)
        lineTo(x + segThkPx, y + bevelPx)
        lineTo(x + segThkPx, y + segLenPx - bevelPx)
        lineTo(x + segThkPx - bevelPx, y + segLenPx)
        lineTo(x + bevelPx, y + segLenPx)
        lineTo(x, y + segLenPx - bevelPx)
        close()
    }

    // Coordonnées de chaque segment
    val A = Offset(segThkPx, 0f)
    val B = Offset(segLenPx + segThkPx, segThkPx)
    val C = Offset(segLenPx + segThkPx, segLenPx + 2 * segThkPx)
    val D = Offset(segThkPx, 2 * segLenPx + 2 * segThkPx)
    val E = Offset(0f, segLenPx + 2 * segThkPx)
    val F = Offset(0f, segThkPx)
    val G = Offset(segThkPx, segLenPx + segThkPx)

    val digitMap = mapOf(
        0 to listOf(true, true, true, true, true, true, false),
        1 to listOf(false, true, true, false, false, false, false),
        2 to listOf(true, true, false, true, true, false, true),
        3 to listOf(true, true, true, true, false, false, true),
        4 to listOf(false, true, true, false, false, true, true),
        5 to listOf(true, false, true, true, false, true, true),
        6 to listOf(true, false, true, true, true, true, true),
        7 to listOf(true, true, true, false, false, false, false),
        8 to listOf(true, true, true, true, true, true, true),
        9 to listOf(true, true, true, true, false, true, true)
    )
    val states = digitMap[digit] ?: List(7) { false }

    val widthPx = segLenPx + 2 * segThkPx
    val heightPx = 2 * segLenPx + 3 * segThkPx

    Canvas(modifier = modifier.size(with(density) { widthPx.toDp() }, with(density) { heightPx.toDp() })) {
        val paintGlow = Paint().apply {
            this.color = onColor
            this.alpha = 255F
            this.asFrameworkPaint().setMaskFilter(android.graphics.BlurMaskFilter(glowRadius, android.graphics.BlurMaskFilter.Blur.NORMAL))
        }

        fun drawSegment(path: Path, isOn: Boolean, index: Int) {
            val flick = flicker(index)
            if (isOn) {
                drawPath(path, onColor.copy(alpha = flick.toFloat()))
                drawContext.canvas.drawPath(path, paintGlow)
            } else {
                drawPath(path, offColor.copy(alpha = alpha * 0.6f))
            }
        }

        drawSegment(segmentHorizontal(A.x, A.y), states[0], 0) // A
        drawSegment(segmentVertical(B.x, B.y), states[1], 1)   // B
        drawSegment(segmentVertical(C.x, C.y), states[2], 2)   // C
        drawSegment(segmentHorizontal(D.x, D.y), states[3], 3) // D
        drawSegment(segmentVertical(E.x, E.y), states[4], 4)   // E
        drawSegment(segmentVertical(F.x, F.y), states[5], 5)   // F
        drawSegment(segmentHorizontal(G.x, G.y), states[6], 6) // G
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSevenSegmentDigitDisplay() {
    Box(
        modifier = Modifier
            .padding(0.dp)
            .background(Color.Black)
    ) {
        SevenSegmentDigitDisplay(
            digit = 8,
            segmentLength = 60.dp,
            segmentThickness = 16.dp,
            bevel = 5.dp,
            onColor = Color.Red,
            offColor = Color(0xFF330000),
            glowRadius = 12f,
            flickerAmplitude = 0.05f,
            flickerFrequency = 2f
        )
    }
}

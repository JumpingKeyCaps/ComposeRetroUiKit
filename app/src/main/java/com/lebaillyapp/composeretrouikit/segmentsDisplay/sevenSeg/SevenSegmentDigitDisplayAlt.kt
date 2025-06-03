package com.lebaillyapp.composeretrouikit.segmentsDisplay.sevenSeg


import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.sin


/**
 * ### SevenSegmentDigitDisplayAlt
 *
 * Version personnalisable d’un afficheur à 7 segments dessiné avec `Canvas`, permettant
 * de définir des longueurs différentes pour les segments **horizontaux** et **verticaux**.
 *
 *  Idéal pour des styles d’affichage compressés, étirés ou artistiques.
 *
 * Effets visuels réalistes :
 * - **Segments biseautés** avec `Path`
 * - **Glow flou** autour des segments allumés
 * - **Scintillement aléatoire** (flicker) subtil et animé
 *
 * ---
 *
 * @param digit Chiffre à afficher (0–9). Si invalide, tous les segments sont éteints.
 * @param modifier Modificateur Compose standard.
 * @param segmentLength Longueur des **segments verticaux** (par défaut 80.dp).
 * @param segmentHorizontalLength Longueur des **segments horizontaux** (permet d’étirer ou aplatir l’affichage).
 * @param segmentThickness Épaisseur d’un segment.
 * @param bevel Taille du biseau appliqué à chaque coin (chanfrein).
 * @param onColor Couleur des segments allumés.
 * @param offColor Couleur des segments éteints (teinte sombre, avec transparence).
 * @param alpha Opacité globale des segments (0f à 1f).
 * @param glowRadius Rayon du flou lumineux autour des segments actifs (en pixels).
 * @param flickerAmplitude Amplitude de variation aléatoire du scintillement.
 * @param flickerFrequency Fréquence d’oscillation du scintillement (en Hz).
 */
@Composable
fun SevenSegmentDigitDisplayAlt(
    digit: Int, // Le chiffre à afficher (0-9)
    modifier: Modifier = Modifier, // Modificateur Jetpack Compose pour personnaliser l'affichage
    segmentLength: Dp = 80.dp, // Longueur des segments verticaux et horizontaux
    segmentHorizontalLength: Dp = 80.dp, // Longueur spécifique des segments horizontaux
    segmentThickness: Dp = 20.dp, // Épaisseur des segments
    bevel: Dp = 6.dp, // Biseau des segments (angle arrondi)
    onColor: Color = Color.Red, // Couleur des segments allumés
    offColor: Color = Color.DarkGray, // Couleur des segments éteints
    alpha: Float = 1f, // Opacité générale des segments
    glowRadius: Float = 15f, // Rayon de l'effet glow (halo lumineux autour des segments)
    flickerAmplitude: Float = 0.1f, // Amplitude de l'effet de clignotement
    flickerFrequency: Float = 3f // Fréquence de clignotement (en Hz)
) {
    // Obtention de la densité des pixels pour effectuer les conversions en pixels à partir des dp
    val density = LocalDensity.current
    val segLenPx = with(density) { segmentLength.toPx() } // Conversion en pixels pour la longueur du segment
    val segHorLenPx = with(density) { segmentHorizontalLength.toPx() } // Conversion en pixels pour la longueur horizontale
    val segThkPx = with(density) { segmentThickness.toPx() } // Conversion en pixels pour l'épaisseur du segment
    val bevelPx = with(density) { bevel.toPx() } // Conversion en pixels pour le biseau

    // Animation infinie pour simuler l'effet de clignotement
    val infiniteTransition = rememberInfiniteTransition()
    val time by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Restart
        )
    )

    // Calcul de l'alpha pour l'effet de clignotement, mélangé avec un bruit aléatoire pour chaque segment
    val flickerAlpha = remember {
        List(7) { kotlin.random.Random(it).nextFloat() * 0.5f + 0.5f } // Variation aléatoire stable [0.5..1]
    }

    // Fonction pour calculer l'intensité du clignotement basé sur le temps (sinusoïdal) et l'indice du segment
    fun flicker(index: Int): Double {
        val base = flickerAlpha.getOrNull(index) ?: 1f
        return alpha * (base + flickerAmplitude * sin(2 * Math.PI * flickerFrequency * time + index))
            .coerceIn(0.0, 1.0)
    }

    // Définition des segments horizontaux et verticaux
    fun segmentHorizontal(x: Float, y: Float): Path = Path().apply {
        moveTo(x + bevelPx, y)
        lineTo(x + segHorLenPx - bevelPx, y)
        lineTo(x + segHorLenPx, y + bevelPx)
        lineTo(x + segHorLenPx, y + segThkPx - bevelPx)
        lineTo(x + segHorLenPx - bevelPx, y + segThkPx)
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

    // Positions des segments sur l'affichage, définies en fonction de l'épaisseur et de la longueur des segments
    val A = Offset(segThkPx, 0f)
    val B = Offset(segHorLenPx + segThkPx, segThkPx)
    val C = Offset(segHorLenPx + segThkPx, segLenPx + 2 * segThkPx)
    val D = Offset(segThkPx, 2 * segLenPx + 2 * segThkPx)
    val E = Offset(0f, segLenPx + 2 * segThkPx)
    val F = Offset(0f, segThkPx)
    val G = Offset(segThkPx, segLenPx + segThkPx)

    // Carte de mappage des chiffres de 0 à 9 sur les segments à allumer
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

    // Détermination des états des segments à partir du chiffre à afficher
    val states = digitMap[digit] ?: List(7) { false }

    // Dimensions du canevas (largeur et hauteur)
    val widthPx = segHorLenPx + 2 * segThkPx
    val heightPx = 2 * segLenPx + 3 * segThkPx

    // Composant Canvas de Jetpack Compose pour dessiner l'affichage
    Canvas(modifier = modifier.size(with(density) { widthPx.toDp() }, with(density) { heightPx.toDp() })) {
        // Définition de l'effet glow avec un filtre de flou autour des segments allumés
        val paintGlow = Paint().apply {
            this.color = onColor
            this.alpha = 255F
            this.asFrameworkPaint().setMaskFilter(android.graphics.BlurMaskFilter(glowRadius, android.graphics.BlurMaskFilter.Blur.NORMAL))
        }


        // Fonction pour dessiner un segment avec un effet de clignotement et de glow si allumé
        fun drawSegment(path: Path, isOn: Boolean, index: Int) {
            val flick = flicker(index)
            if (isOn) {
                // Dessiner le segment avec glow et clignotement
                drawPath(path, onColor.copy(alpha = flick.toFloat()))
                // Dessiner le glow (flou)
                drawContext.canvas.drawPath(path, paintGlow)
            } else {
                // Dessiner le segment éteint avec une opacité réduite
                drawPath(path, offColor.copy(alpha = alpha * 0.6f))
            }
        }

        // Dessiner chaque segment à partir de leurs positions et états
        drawSegment(segmentHorizontal(A.x, A.y), states[0], 0)
        drawSegment(segmentVertical(B.x, B.y), states[1], 1)
        drawSegment(segmentVertical(C.x, C.y), states[2], 2)
        drawSegment(segmentHorizontal(D.x, D.y), states[3], 3)
        drawSegment(segmentVertical(E.x, E.y), states[4], 4)
        drawSegment(segmentVertical(F.x, F.y), states[5], 5)
        drawSegment(segmentHorizontal(G.x, G.y), states[6], 6)
    }
}
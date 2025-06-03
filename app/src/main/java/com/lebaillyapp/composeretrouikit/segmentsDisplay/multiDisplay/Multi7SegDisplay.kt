package com.lebaillyapp.composeretrouikit.segmentsDisplay.multiDisplay

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.lebaillyapp.composeretrouikit.segmentsDisplay.sevenSeg.SevenSegmentDigitDisplayExtended

/**
 * ### Multi7SegDisplay
 *
 * Affiche dynamiquement une rangée horizontale de chiffres ou caractères au format 7 segments.
 *
 * Ce composant s'appuie sur une liste de configurations [SevenSegmentConfig] pour afficher chaque élément.
 * Il peut afficher une valeur en override via [overrideValue] (ex: une string comme "1234"), avec la possibilité
 * d’inverser l’ordre ([reversedOverride]), ou de forcer l’affichage du zéro lorsque vide.
 *
 * Une réflexion peut également être affichée en dessous si [activateReflect] est à `true`, avec configuration via [reflectConfig].
 *
 * ### Fonctionnalités :
 * - Affichage de chiffres, lettres, ou segments personnalisés.
 * - Spacing ajustable entre les chiffres et espacement supplémentaire conditionnel ([extraSpacingStep], [extraSpacing]).
 * - Mode override avec alignement normal ou inversé.
 * - Affichage de réflexion stylisée (angle, alpha, distance...).
 *
 * @param modifier `Modifier` standard pour appliquer positionnement, taille, etc.
 * @param configs Liste des configurations individuelles pour chaque segment via [SevenSegmentConfig].
 * @param overrideValue Chaîne de caractères à afficher explicitement à la place des configs si non null.
 * @param reversedOverride Inverse l’ordre de lecture de [overrideValue] si `true` (utile pour affichage droite→gauche).
 * @param spacing Espacement standard entre chaque afficheur.
 * @param showZeroWhenEmpty Affiche un 0 à la place d’un chiffre/lettre manquants si `true`, sinon segment éteint.
 * @param extraSpacingStep Nombre d'afficheurs avant insertion d’un espacement additionnel (0 = désactivé).
 * @param extraSpacing Espacement supplémentaire à insérer tous les [extraSpacingStep] afficheurs.
 * @param activateReflect Active ou désactive l'affichage de la réflexion.
 * @param reflectConfig Paramètres de configuration pour la réflexion (angle, alpha, décalage, glow...).
 */
@Composable
fun Multi7SegDisplay(
    modifier: Modifier = Modifier,
    configs: List<SevenSegmentConfig>,
    overrideValue: String? = null,
    reversedOverride: Boolean = false,
    spacing: Dp = 8.dp,
    showZeroWhenEmpty: Boolean = false,
    extraSpacingStep: Int = 0, // tous les N afficheurs (0 = désactivé)
    extraSpacing: Dp = 0.dp,
    activateReflect: Boolean = false,
    reflectConfig: ReflectConfig
) {
    Column(modifier = modifier) {
        //Affichage des chiffres
        Row(
            modifier = modifier.align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            configs.forEachIndexed { index, config ->
                if (index > 0) {
                    val addExtra = extraSpacingStep > 0 && index % extraSpacingStep == 0
                    val totalSpacing = spacing + if (addExtra) extraSpacing else 0.dp
                    Spacer(modifier = Modifier.width(totalSpacing))
                }

                val overrideChar = when {
                    overrideValue == null -> null

                    reversedOverride -> {
                        val charIndex = overrideValue.length - configs.size + index
                        overrideValue.getOrNull(charIndex)
                    }

                    else -> overrideValue.getOrNull(index)
                }

                val digitOverride = overrideChar?.digitToIntOrNull()
                val charOverride = if (digitOverride == null) overrideChar else null

                val displayDigit = when {
                    overrideChar != null -> digitOverride
                    showZeroWhenEmpty    -> 0
                    else                 -> null
                }

                val displayChar = when {
                    overrideChar != null -> charOverride
                    showZeroWhenEmpty    -> null
                    else                 -> null
                }

                val displayManual = if (overrideChar == null && !showZeroWhenEmpty) {
                    List(7) { false }
                } else config.manualSegments

                SevenSegmentDigitDisplayExtended(
                    digit = displayDigit ?: config.digit,
                    char = displayChar ?: config.char,
                    manualSegments = displayManual,
                    segmentLength = config.segmentLength,
                    segmentHorizontalLength = config.segmentHorizontalLength,
                    segmentThickness = config.segmentThickness,
                    bevel = config.bevel,
                    onColor = config.onColor,
                    offColor = config.offColor,
                    alpha = config.alpha,
                    glowRadius = config.glowRadius,
                    flickerAmplitude = config.flickerAmplitude,
                    flickerFrequency = config.flickerFrequency,
                    idleMode = config.idleMode,
                    idleSpeed = config.idleSpeed
                )
            }
        }

        //REFLEXION
        if (activateReflect) {
            Spacer(modifier = Modifier.height(reflectConfig.reflectSpacing))
            Row(
                modifier = modifier.alpha(reflectConfig.reflectAlpha).graphicsLayer {
                    rotationY = 0f
                    rotationX = reflectConfig.reflectAngle
                    cameraDistance = reflectConfig.reflectCameraAdjustment * density
                }.align(Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(spacing))
                configs.forEachIndexed { index, config ->
                    if (index > 0) {
                        val addExtra = extraSpacingStep > 0 && index % extraSpacingStep == 0
                        val totalSpacing = spacing + if (addExtra) extraSpacing else 0.dp
                        Spacer(modifier = Modifier.width(totalSpacing))
                    }

                    val overrideChar = when {
                        overrideValue == null -> null

                        reversedOverride -> {
                            val charIndex = overrideValue.length - configs.size + index
                            overrideValue.getOrNull(charIndex)
                        }

                        else -> overrideValue.getOrNull(index)
                    }

                    val digitOverride = overrideChar?.digitToIntOrNull()
                    val charOverride = if (digitOverride == null) overrideChar else null

                    val displayDigit = when {
                        overrideChar != null -> digitOverride
                        showZeroWhenEmpty    -> 0
                        else                 -> null
                    }

                    val displayChar = when {
                        overrideChar != null -> charOverride
                        showZeroWhenEmpty    -> null
                        else                 -> null
                    }

                    val displayManual = if (overrideChar == null && !showZeroWhenEmpty) {
                        List(7) { false }
                    } else config.manualSegments

                    SevenSegmentDigitDisplayExtended(
                        digit = displayDigit ?: config.digit,
                        char = displayChar ?: config.char,
                        manualSegments = displayManual,
                        segmentLength = config.segmentLength,
                        segmentHorizontalLength = config.segmentHorizontalLength,
                        segmentThickness = config.segmentThickness,
                        bevel = config.bevel,
                        onColor = config.onColor,
                        offColor = config.offColor,
                        alpha = config.alpha,
                        glowRadius = reflectConfig.reflectGlowRadius,
                        flickerAmplitude = config.flickerAmplitude,
                        flickerFrequency = config.flickerFrequency,
                        idleMode = config.idleMode,
                        idleSpeed = config.idleSpeed
                    )
                }
                Spacer(modifier = Modifier.width(spacing))
            }
        }


    }
}

data class SevenSegmentConfig(
    val digit: Int? = null,
    val char: Char? = null,
    val manualSegments: List<Boolean>? = null,
    val segmentLength: Dp = 15.dp,
    val segmentHorizontalLength: Dp = 15.dp,
    val segmentThickness: Dp = 3.dp,
    val bevel: Dp = 1.dp,
    val onColor: Color = Color.Red,
    val offColor: Color = Color.DarkGray,
    val alpha: Float = 1f,
    val glowRadius: Float = 15f,
    val flickerAmplitude: Float = 0.25f,
    val flickerFrequency: Float = 1f,
    val idleMode: Boolean = false,
    val idleSpeed: Long = 100

)

data class ReflectConfig(
    val reflectAlpha: Float = 0.3f,
    val reflectSpacing: Dp = 0.dp,
    val reflectAngle: Float = 245f,
    val reflectGlowRadius: Float = 20f,
    val reflectCameraAdjustment: Float = 6f

)
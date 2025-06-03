package com.lebaillyapp.composeretrouikit.segmentsDisplay.sevenSeg

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 *
 * ### SevenSegmentDisplay
 *
 * Composable  d'affichage 7 segments réaliste, intégrant rotation 3D, reflet configurable et effets visuels avancés.
 *
 * Ce composant regroupe et étend les fonctionnalités de `SevenSegmentDigitDisplayExtended`.
 * Il permet d'afficher un chiffre avec :
 * - un angle de vue dynamique (rotation X/Y + distance caméra),
 * - des effets de glow, flicker et couleur personnalisables,
 * - un reflet optionnel ajustable indépendamment (intensité, glow, transparence),
 * - une adaptation complète via `Modifier` pour positionner/transformer le composant.
 *
 * @param modifier Modificateur Compose pour positionner et transformer l'ensemble du composant (ex : décalage, scale...).
 * @param rotationYAngle Rotation autour de l'axe Y (horizontal), en degrés. Permet de simuler une vue en perspective latérale.
 * @param rotationXAngle Rotation autour de l'axe X (vertical), en degrés. Permet de simuler une vue en plongée/contre-plongée.
 * @param cameraAdjustment Facteur de distance caméra (plus élevé = effet de perspective plus faible). Par défaut 4f.
 * @param digit Le chiffre à afficher (0 à 9). Doit être compatible avec les segments 7-segments.
 * @param digitColor Couleur principale des segments allumés (souvent une teinte rouge ou verte pour un effet LED).
 * @param digitGlowRadius Rayon du glow appliqué aux segments principaux (hors reflet). Plus élevé = glow plus diffus.
 * @param digitReflectGlowRadius Rayon du glow appliqué au reflet si activé. Peut être différent du glow principal.
 * @param digitFlickerAmplitude Amplitude de l’effet de scintillement des segments (entre 0 et 1). 0 = aucun flicker.
 * @param digitFlickerFrequency Fréquence du scintillement en Hz. Plus élevé = scintillement rapide.
 * @param offColor Couleur des segments éteints (souvent un gris très foncé ou noir légèrement bleuté).
 * @param segmentLength Longueur des segments verticaux (par défaut égale à la longueur horizontale pour un look carré).
 * @param segmentHorizontalLength Longueur spécifique pour les segments horizontaux. Permet d'écraser ou étirer l'affichage.
 * @param segmentThickness Épaisseur de chaque segment. Détermine le poids visuel de chaque barre.
 * @param bevel Taille du biseau visuel des segments pour accentuer l’effet 3D (visuellement réaliste).
 * @param alpha Opacité principale de l'affichage (entre 0 et 1). 1 = opaque, 0 = invisible.
 * @param alphaReflect Opacité du reflet si activé. Souvent plus faible (0.1 – 0.3) pour un reflet subtil.
 * @param reflectActivated Active ou désactive l'affichage du reflet sous le chiffre principal.
 *
 */
@Composable
fun SevenSegmentDisplay(
    modifier: Modifier = Modifier,
    rotationYAngle: Float = 0f,
    rotationXAngle: Float = 0f,
    cameraAdjustment: Float = 4f,
    digit: Int = 0,
    digitColor: Color = Color.Red,
    digitGlowRadius: Float = 70f,
    digitReflectGlowRadius: Float = 70f,
    digitFlickerAmplitude: Float = 0.05f,
    digitFlickerFrequency: Float = 1f,
    offColor: Color = Color(0xFF252222),
    segmentLength: Dp = 17.5.dp,
    segmentHorizontalLength: Dp = 17.5.dp,
    segmentThickness: Dp = 4.5.dp,
    bevel: Dp = 2.dp,
    alpha: Float = 1f,
    alphaReflect: Float = 0.2f,
    reflectActivated: Boolean = false
){

    Column(modifier = modifier
        .graphicsLayer {
            rotationY = rotationYAngle
            rotationX = rotationXAngle
            cameraDistance = cameraAdjustment * density
        }
    ){
        SevenSegmentDigitDisplayExtended(
            digit = digit,
            glowRadius = digitGlowRadius,
            flickerAmplitude = digitFlickerAmplitude,
            flickerFrequency = digitFlickerFrequency,
            alpha = alpha,
            segmentLength = segmentLength,
            segmentHorizontalLength = segmentHorizontalLength,
            segmentThickness = segmentThickness,
            bevel = bevel,
            onColor = digitColor,
            offColor = offColor,
            modifier = Modifier
        )
        if(reflectActivated){
            Box(modifier = Modifier
                .graphicsLayer {
                    rotationY = 0f
                    rotationX = 245f
                    cameraDistance = 4f * density
                }
            ){
                SevenSegmentDigitDisplayExtended(
                    digit = digit,
                    glowRadius = digitReflectGlowRadius,
                    flickerAmplitude = digitFlickerAmplitude,
                    flickerFrequency = digitFlickerFrequency,
                    alpha = alphaReflect,
                    segmentLength = segmentLength,
                    segmentHorizontalLength = segmentHorizontalLength,
                    segmentThickness = segmentThickness,
                    bevel = bevel,
                    onColor = digitColor,
                    offColor = offColor,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

        }
    }




}

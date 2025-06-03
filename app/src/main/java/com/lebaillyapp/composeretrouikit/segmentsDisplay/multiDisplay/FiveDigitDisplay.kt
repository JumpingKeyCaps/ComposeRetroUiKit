package com.lebaillyapp.composeretrouikit.segmentsDisplay.multiDisplay

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.lebaillyapp.composeretrouikit.segmentsDisplay.sevenSeg.SevenSegmentDigitDisplayAlt

/**
 *
 * ### FiveDigitDisplay
 *
 * Composable affichant un ensemble de cinq digits à 7 segments personnalisables, alignés horizontalement.
 *
 * Chaque chiffre utilise le composant `SevenSegmentDisplayVariante` avec effets visuels réalistes (biseau, glow, flicker),
 * et peut être configuré individuellement pour sa couleur, intensité de glow, flicker, et sa valeur numérique.
 *
 * @param modifier [Modifier] à appliquer au conteneur global (Row).
 * @param rotationYAngle Angle de rotation sur l'axe Y pour effet 3D.
 * @param rotationXAngle Angle de rotation sur l'axe X pour effet 3D.
 * @param cameraAdjustment Facteur de distance de la caméra virtuelle pour contrôler la profondeur de perspective.
 * @param digit1 Valeur du premier chiffre à afficher (0–9).
 * @param digit2 Valeur du deuxième chiffre à afficher (0–9).
 * @param digit3 Valeur du troisième chiffre à afficher (0–9).
 * @param digit4 Valeur du quatrième chiffre à afficher (0–9).
 * @param digit5 Valeur du cinquième chiffre à afficher (0–9).
 * @param digit1Color Couleur d’activation des segments du premier chiffre.
 * @param digit2Color Couleur d’activation des segments du deuxième chiffre.
 * @param digit3Color Couleur d’activation des segments du troisième chiffre.
 * @param digit4Color Couleur d’activation des segments du quatrième chiffre.
 * @param digit5Color Couleur d’activation des segments du cinquième chiffre.
 * @param digit1GlowRadius Rayon du glow pour le premier chiffre.
 * @param digit2GlowRadius Rayon du glow pour le deuxième chiffre.
 * @param digit3GlowRadius Rayon du glow pour le troisième chiffre.
 * @param digit4GlowRadius Rayon du glow pour le quatrième chiffre.
 * @param digit5GlowRadius Rayon du glow pour le cinquième chiffre.
 * @param digit1FlickerAmplitude Amplitude de flicker (variation d'intensité lumineuse) du premier chiffre.
 * @param digit2FlickerAmplitude Amplitude de flicker du deuxième chiffre.
 * @param digit3FlickerAmplitude Amplitude de flicker du troisième chiffre.
 * @param digit4FlickerAmplitude Amplitude de flicker du quatrième chiffre.
 * @param digit5FlickerAmplitude Amplitude de flicker du cinquième chiffre.
 * @param digit1FlickerFrequency Fréquence du flicker du premier chiffre.
 * @param digit2FlickerFrequency Fréquence du flicker du deuxième chiffre.
 * @param digit3FlickerFrequency Fréquence du flicker du troisième chiffre.
 * @param digit4FlickerFrequency Fréquence du flicker du quatrième chiffre.
 * @param digit5FlickerFrequency Fréquence du flicker du cinquième chiffre.
 * @param offColor Couleur des segments éteints (par défaut un gris foncé).
 * @param segmentLength Longueur des segments verticaux.
 * @param segmentHorizontalLength Longueur des segments horizontaux.
 * @param segmentThickness Épaisseur de chaque segment.
 * @param bevel Taille du biseau appliqué à chaque segment.
 * @param alpha Opacité globale appliquée à tous les digits.
 * @param spacer Espacement horizontal entre chaque chiffre.
 */
@Composable
fun FiveDigitDisplay(
    modifier: Modifier = Modifier,
    rotationYAngle: Float = 0f,
    rotationXAngle: Float = 0f,
    cameraAdjustment: Float = 4f,
    digit1: Int = 0,
    digit2: Int = 0,
    digit3: Int = 0,
    digit4: Int = 0,
    digit5: Int = 0,
    digit1Color: Color = Color.Red,
    digit2Color: Color = Color.Red,
    digit3Color: Color = Color.Red,
    digit4Color: Color = Color.Red,
    digit5Color: Color = Color.Red,
    digit1GlowRadius: Float = 70f,
    digit2GlowRadius: Float = 70f,
    digit3GlowRadius: Float = 70f,
    digit4GlowRadius: Float = 70f,
    digit5GlowRadius: Float = 70f,
    digit1FlickerAmplitude: Float = 0.05f,
    digit2FlickerAmplitude: Float = 0.15f,
    digit3FlickerAmplitude: Float = 0.10f,
    digit4FlickerAmplitude: Float = 0.20f,
    digit5FlickerAmplitude: Float = 0.1f,
    digit1FlickerFrequency: Float = 1f,
    digit2FlickerFrequency: Float = 0.5f,
    digit3FlickerFrequency: Float = 1f,
    digit4FlickerFrequency: Float = 1.0f,
    digit5FlickerFrequency: Float = 1.0f,
    offColor: Color = Color(0xFF252222),
    segmentLength: Dp = 17.5.dp,
    segmentHorizontalLength: Dp = 17.5.dp,
    segmentThickness: Dp = 4.5.dp,
    bevel: Dp = 2.dp,
    alpha: Float = 1f,
    spacer: Dp = 10.dp
){
    Row(modifier = modifier
        .graphicsLayer {
            rotationY = rotationYAngle
            rotationX = rotationXAngle
            // cameraDistance est important pour éviter l'étirement du composant lors de la rotation 3D
            // Une valeur de 8f est souvent un bon point de départ.
            // Vous pouvez l'ajuster en fonction de l'effet désiré.
            cameraDistance = cameraAdjustment * density
        }


    ) {

        SevenSegmentDigitDisplayAlt(
            digit = digit1,
            glowRadius = digit1GlowRadius,
            flickerAmplitude = digit1FlickerAmplitude,
            flickerFrequency = digit1FlickerFrequency,
            alpha = alpha,
            segmentLength = segmentLength,
            segmentHorizontalLength = segmentHorizontalLength,
            segmentThickness = segmentThickness,
            bevel = bevel,
            onColor = digit1Color,
            offColor = offColor,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.width(spacer))
        SevenSegmentDigitDisplayAlt(
            digit = digit2,
            glowRadius = digit2GlowRadius,
            flickerAmplitude = digit2FlickerAmplitude,
            flickerFrequency = digit2FlickerFrequency,
            alpha = alpha,
            segmentLength = segmentLength,
            segmentHorizontalLength = segmentHorizontalLength,
            segmentThickness = segmentThickness,
            bevel = bevel,
            onColor = digit2Color,
            offColor = offColor,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.width(spacer))
        SevenSegmentDigitDisplayAlt(
            digit = digit3,
            glowRadius = digit3GlowRadius,
            flickerAmplitude = digit3FlickerAmplitude,
            flickerFrequency = digit3FlickerFrequency,
            alpha = alpha,
            segmentLength = segmentLength,
            segmentHorizontalLength = segmentHorizontalLength,
            segmentThickness = segmentThickness,
            bevel = bevel,
            onColor = digit3Color,
            offColor = offColor,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.width(spacer))
        SevenSegmentDigitDisplayAlt(
            digit = digit4,
            glowRadius = digit4GlowRadius,
            flickerAmplitude = digit4FlickerAmplitude,
            flickerFrequency = digit4FlickerFrequency,
            alpha = alpha,
            segmentLength = segmentLength,
            segmentHorizontalLength = segmentHorizontalLength,
            segmentThickness = segmentThickness,
            bevel = bevel,
            onColor = digit4Color,
            offColor = offColor,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.width(spacer))
        SevenSegmentDigitDisplayAlt(
            digit = digit5,
            glowRadius = digit5GlowRadius,
            flickerAmplitude = digit5FlickerAmplitude,
            flickerFrequency = digit5FlickerFrequency,
            alpha = alpha,
            segmentLength = segmentLength,
            segmentHorizontalLength = segmentHorizontalLength,
            segmentThickness = segmentThickness,
            bevel = bevel,
            onColor = digit5Color,
            offColor = offColor,
            modifier = Modifier
        )


    }
}
package com.lebaillyapp.composeretrouikit.segmentsDisplay.multiDisplay

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.lebaillyapp.composeretrouikit.segmentsDisplay.fourteenSeg.FourteenSegmentConfig
import com.lebaillyapp.composeretrouikit.segmentsDisplay.fourteenSeg.FourteenSegmentDisplay

/**
 * ### Multi14SegDisplay
 *
 * Afficheur LED photoréaliste 14 segments multiple,
 * pour afficher une chaîne de caractères ou une liste de chiffres.
 *
 * Chaque caractère est affiché avec FourteenSegmentDisplay côte à côte,
 * avec un espacement configurable.
 *
 * @param modifier Modifier Jetpack Compose pour la taille globale.
 * @param text Chaîne de caractères à afficher (lettres majuscules, chiffres).
 * @param digits Liste de chiffres (0-9) à afficher si text est nul.
 * @param config Configuration FourteenSegmentConfig appliquée à chaque affichage.
 * @param spacingDp Espacement horizontal entre les caractères en dp.
 */
@Composable
fun Multi14SegDisplay(
    modifier: Modifier = Modifier,
    text: String? = null,
    digits: List<Int>? = null,
    config: FourteenSegmentConfig = FourteenSegmentConfig(),
    spacingDp: Dp = 4.dp,
) {
    val density = LocalDensity.current
    val segmentWidthPx = with(density) { (config.segmentHorizontalLength.toPx() + 2 * config.segmentThickness.toPx()) }
    val spacingPx = with(density) { spacingDp.toPx() }

    // On détermine la liste des caractères à afficher
    val charList: List<Char?> = when {
        text != null -> text.toUpperCase().map { it }
        digits != null -> digits.map { null } // On affichera digits individuellement
        else -> emptyList()
    }

    // Taille pour chaque FourteenSegmentDisplay
    val widthPerCharDp = with(density) { (segmentWidthPx).toDp() }
    val heightPerCharDp = with(density) { (2 * config.segmentLength.toPx() + 3 * config.segmentThickness.toPx()).toDp() }

    Row(
        modifier = modifier.height(heightPerCharDp),
        horizontalArrangement = Arrangement.spacedBy(spacingDp)
    ) {
        if (text != null) {
            // Affichage caractères (lettres)
            text.toUpperCase().forEach { c ->
                FourteenSegmentDisplay(
                    modifier = Modifier.size(widthPerCharDp, heightPerCharDp),
                    config = config,
                    char = c,
                    digit = null,
                    manualSegments = null
                )
            }
        } else if (digits != null) {
            // Affichage chiffres
            digits.forEach { d ->
                FourteenSegmentDisplay(
                    modifier = Modifier.size(widthPerCharDp, heightPerCharDp),
                    config = config,
                    char = null,
                    digit = d,
                    manualSegments = null
                )
            }
        }
    }
}

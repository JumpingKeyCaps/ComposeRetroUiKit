package com.lebaillyapp.composeretrouikit.demoScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lebaillyapp.composeretrouikit.segmentsDisplay.fourteenSeg.FourteenSegmentConfig
import com.lebaillyapp.composeretrouikit.segmentsDisplay.fourteenSeg.FourteenSegmentDisplay
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun Demo14segmentDisplay(){

    val config14segA = FourteenSegmentConfig(
        digit = null,
        char = null,
        manualSegments = null,
        segmentLength = 21.5.dp,
        segmentHorizontalLength = 13.5.dp,
        segmentThickness = 3.5.dp,
        bevel = 2.dp,
        onColor = Color(0xFFF50057),
        offColor = Color(0xFF232525),
        alpha = 1f,
        glowRadius = 35f,
        flickerAmplitude = 0.25f,
        flickerFrequency = 4f,
        idleMode = false,
        idleSpeed = 100
    )

    val config14segB = FourteenSegmentConfig(
        digit = null,
        char = null,
        manualSegments = null,
        segmentLength = 20.dp,
        segmentHorizontalLength = 12.dp,
        segmentThickness = 3.dp,
        bevel = 1.5.dp,
        onColor = Color(0xFFF50057),
        offColor = Color(0xFF232525),
        alpha = 1f,
        glowRadius = 35f,
        flickerAmplitude = 0.25f,
        flickerFrequency = 4f,
        idleMode = false,
        idleSpeed = 100
    )
    val config14segC = FourteenSegmentConfig(
        digit = null,
        char = null,
        manualSegments = null,
        segmentLength = 28.dp,
        segmentHorizontalLength = 28.dp,
        segmentThickness = 6.dp,
        bevel = 3.dp,
        onColor = Color(0xFF1DE9B6),
        offColor = Color(0xFF232525),
        alpha = 1f,
        glowRadius = 35f,
        flickerAmplitude = 0.25f,
        flickerFrequency = 4f,
        idleMode = false,
        idleSpeed = 100
    )
    val config14segD = FourteenSegmentConfig(
        digit = null,
        char = null,
        manualSegments = null,
        segmentLength = 28.dp,
        segmentHorizontalLength = 28.dp,
        segmentThickness = 6.dp,
        bevel = 3.dp,
        onColor = Color(0xFFFFC400),
        offColor = Color(0xFF232525),
        alpha = 1f,
        glowRadius = 35f,
        flickerAmplitude = 0.25f,
        flickerFrequency = 4f,
        idleMode = false,
        idleSpeed = 100
    )


    var TxtBis by remember { mutableStateOf(" 14-SEGMENT") }
    val spacerA by remember { mutableStateOf(8.dp) }


    var TxtBisB by remember { mutableStateOf("DISPLAY") }
    val spacerB by remember { mutableStateOf(11.dp) }

    var countC by remember { mutableStateOf(0) }

    var letterCount by remember { mutableStateOf(0) }
    var refAlphabet by remember { mutableStateOf("ABCDEFGHIJKLMNOPQRSTUVWXYZ") }
    var refAlphabetLow by remember { mutableStateOf("abcdefghijklmnopqrstuvwxyz") }

    LaunchedEffect(Unit) {
        while (true) {
            if (countC >= 9) countC = 0 else countC++
            if (letterCount >= refAlphabet.length) letterCount = 0 else letterCount++
            delay(1000)
        }
    }


    Column {
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            FourteenSegmentDisplay(
                modifier = Modifier.align(Alignment.CenterVertically),
                config = config14segA,
                char = TxtBis[0]
            )
            Spacer(modifier = Modifier.size(spacerA))
            FourteenSegmentDisplay(
                modifier = Modifier.align(Alignment.CenterVertically),
                config = config14segA,
                char = TxtBis[1]
            )
            Spacer(modifier = Modifier.size(spacerA))
            FourteenSegmentDisplay(
                modifier = Modifier.align(Alignment.CenterVertically),
                config = config14segA,
                char = TxtBis[2]
            )
            Spacer(modifier = Modifier.size(spacerA))
            FourteenSegmentDisplay(
                modifier = Modifier.align(Alignment.CenterVertically),
                config = config14segA,
                char = TxtBis[3]
            )
            Spacer(modifier = Modifier.size(spacerA))
            FourteenSegmentDisplay(
                modifier = Modifier.align(Alignment.CenterVertically),
                config = config14segA,
                char = TxtBis[4]
            )
            Spacer(modifier = Modifier.size(spacerA))
            FourteenSegmentDisplay(
                modifier = Modifier.align(Alignment.CenterVertically),
                config = config14segA,
                char = TxtBis[5]
            )
            Spacer(modifier = Modifier.size(spacerA))
            FourteenSegmentDisplay(
                modifier = Modifier.align(Alignment.CenterVertically),
                config = config14segA,
                char = TxtBis[6]
            )
            Spacer(modifier = Modifier.size(spacerA))
            FourteenSegmentDisplay(
                modifier = Modifier.align(Alignment.CenterVertically),
                config = config14segA,
                char = TxtBis[7]
            )
            Spacer(modifier = Modifier.size(spacerA))
            FourteenSegmentDisplay(
                modifier = Modifier.align(Alignment.CenterVertically),
                config = config14segA,
                char = TxtBis[8]
            )
            Spacer(modifier = Modifier.size(spacerA))
            FourteenSegmentDisplay(
                modifier = Modifier.align(Alignment.CenterVertically),
                config = config14segA,
                char = TxtBis[9]
            )
            Spacer(modifier = Modifier.size(spacerA))
            FourteenSegmentDisplay(
                modifier = Modifier.align(Alignment.CenterVertically),
                config = config14segA,
                char = TxtBis[10]
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            FourteenSegmentDisplay(
                modifier = Modifier.align(Alignment.CenterVertically),
                config = config14segB,
                char = TxtBisB[0]
            )
            Spacer(modifier = Modifier.size(spacerB))
            FourteenSegmentDisplay(
                modifier = Modifier.align(Alignment.CenterVertically),
                config = config14segB,
                char = TxtBisB[1]
            )
            Spacer(modifier = Modifier.size(spacerB))
            FourteenSegmentDisplay(
                modifier = Modifier.align(Alignment.CenterVertically),
                config = config14segB,
                char = TxtBisB[2]
            )
            Spacer(modifier = Modifier.size(spacerB))
            FourteenSegmentDisplay(
                modifier = Modifier.align(Alignment.CenterVertically),
                config = config14segB,
                char = TxtBisB[3]
            )
            Spacer(modifier = Modifier.size(spacerB))
            FourteenSegmentDisplay(
                modifier = Modifier.align(Alignment.CenterVertically),
                config = config14segB,
                char = TxtBisB[4]
            )
            Spacer(modifier = Modifier.size(spacerB))
            FourteenSegmentDisplay(
                modifier = Modifier.align(Alignment.CenterVertically),
                config = config14segB,
                char = TxtBisB[5]
            )
            Spacer(modifier = Modifier.size(spacerB))
            FourteenSegmentDisplay(
                modifier = Modifier.align(Alignment.CenterVertically),
                config = config14segB,
                char = TxtBisB[6]
            )
        }
        Spacer(modifier = Modifier.height(60.dp))
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {

            FourteenSegmentDisplay(
                modifier = Modifier.align(Alignment.CenterVertically),
                config = config14segD,
                char = refAlphabet.elementAtOrNull(letterCount)
            )
            Spacer(modifier = Modifier.size(spacerB))
            FourteenSegmentDisplay(
                modifier = Modifier.align(Alignment.CenterVertically),
                config = config14segD,
                char = refAlphabetLow.elementAtOrNull(letterCount)
            )
            Spacer(modifier = Modifier.size(20.dp))
            FourteenSegmentDisplay(
                modifier = Modifier.align(Alignment.CenterVertically),
                config = config14segC,
                char = if(countC == 1){'1'}else{ countC.digitToChar()}
            )

        }


    }




}
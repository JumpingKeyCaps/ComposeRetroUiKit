package com.lebaillyapp.composeretrouikit.demoScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.lebaillyapp.composeretrouikit.segmentsDisplay.multiDisplay.FiveDigitDisplayWithReflect
import com.lebaillyapp.composeretrouikit.segmentsDisplay.multiDisplay.Multi7SegDisplay
import com.lebaillyapp.composeretrouikit.segmentsDisplay.multiDisplay.ReflectConfig
import com.lebaillyapp.composeretrouikit.segmentsDisplay.multiDisplay.SevenSegmentConfig
import com.lebaillyapp.composeretrouikit.segmentsDisplay.multiDisplay.iteratorConfigGenerator
import kotlinx.coroutines.delay

@Composable
fun Demo_7segmentDisplay(){

    var DynamicDemoCompteur by remember { mutableStateOf(0) }
    var DynamicDemoCompteurB by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1)
            DynamicDemoCompteur+=4
        }
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(100)
            DynamicDemoCompteurB++
        }
    }



    // big config
    val segLgt = 15.dp
    val segHorLgt = 15.dp
    val segThk = 4.dp
    val bevel = 2.dp
    val offCol = Color(0xFF332F21)
    val onCol = Color(0xFF1BFA99)
    val flickAmpl = 0.15f
    val flickFreq = 1f
    val glowRad = 70f

    var idletrigger by remember { mutableStateOf(false) }

    //reflection config
    val reflectionConfig = ReflectConfig(
        reflectAlpha = 0.3f,
        reflectSpacing = 0.dp,
        reflectAngle = 245f,
        reflectGlowRadius = 35f,
        reflectCameraAdjustment = 4f
    )



    val configSegments = iteratorConfigGenerator(
        sevenSegCfg = SevenSegmentConfig(
            digit = null,
            char = null,
            manualSegments = null,
            segmentLength = segLgt,
            segmentHorizontalLength = segHorLgt,
            segmentThickness = segThk,
            bevel = bevel,
            onColor = onCol,
            offColor = offCol,
            alpha = 1f,
            glowRadius = glowRad,
            flickerAmplitude = flickAmpl,
            flickerFrequency = flickFreq,
            idleMode = idletrigger,
            idleSpeed = 100
        ),
        nbrDigit = 10
    )


    val configSegments5 = listOf(
        SevenSegmentConfig(
            digit = null,
            char = null,
            manualSegments = null,
            segmentLength = 20.dp,
            segmentHorizontalLength = 65.dp,
            segmentThickness = 12.dp,
            bevel = 5.dp,
            onColor = Color(0xFFFFFFFF),
            offColor = Color(0xFF332F21),
            alpha = 1f,
            glowRadius = 60f,
            flickerAmplitude = 0.25f,
            flickerFrequency = 1f,
            idleMode = false,
            idleSpeed = 100
        ),
        SevenSegmentConfig(
            digit = null,
            char = null,
            manualSegments = null,
            segmentLength = 20.dp,
            segmentHorizontalLength = 65.dp,
            segmentThickness = 12.dp,
            bevel = 5.dp,
            onColor = Color(0xFFFFFFFF),
            offColor = Color(0xFF332F21),
            alpha = 1f,
            glowRadius = 60f,
            flickerAmplitude = 0.25f,
            flickerFrequency = 1f,
            idleMode = false,
            idleSpeed = 100
        ),
        SevenSegmentConfig(
            digit = null,
            char = null,
            manualSegments = null,
            segmentLength = 20.dp,
            segmentHorizontalLength = 65.dp,
            segmentThickness = 12.dp,
            bevel = 5.dp,
            onColor = Color(0xFFF50057),
            offColor = Color(0xFF332F21),
            alpha = 1f,
            glowRadius = 60f,
            flickerAmplitude = 0.25f,
            flickerFrequency = 1f,
            idleMode = false,
            idleSpeed = 100
        ),
    )

    val configSegments6 = listOf(
        SevenSegmentConfig(
            digit = null,
            char = null,
            manualSegments = null,
            segmentLength = 40.dp,
            segmentHorizontalLength = 14.dp,
            segmentThickness = 7.dp,
            bevel = 3.5.dp,
            onColor = Color(0xFFC6FF00),
            offColor = Color(0xFF332F21),
            alpha = 1f,
            glowRadius = 60f,
            flickerAmplitude = 0.25f,
            flickerFrequency = 1f,
            idleMode = false,
            idleSpeed = 100
        ),
        SevenSegmentConfig(
            digit = null,
            char = null,
            manualSegments = null,
            segmentLength = 40.dp,
            segmentHorizontalLength = 14.dp,
            segmentThickness = 7.dp,
            bevel = 3.5.dp,
            onColor = Color(0xFFC6FF00),
            offColor = Color(0xFF332F21),
            alpha = 1f,
            glowRadius = 60f,
            flickerAmplitude = 0.25f,
            flickerFrequency = 1f,
            idleMode = false,
            idleSpeed = 100
        ),
        SevenSegmentConfig(
            digit = null,
            char = null,
            manualSegments = null,
            segmentLength = 40.dp,
            segmentHorizontalLength = 14.dp,
            segmentThickness = 7.dp,
            bevel = 3.5.dp,
            onColor = Color(0xFFC6FF00),
            offColor = Color(0xFF332F21),
            alpha = 1f,
            glowRadius = 60f,
            flickerAmplitude = 0.25f,
            flickerFrequency = 1f,
            idleMode = false,
            idleSpeed = 100
        ),
        SevenSegmentConfig(
            digit = null,
            char = null,
            manualSegments = null,
            segmentLength = 40.dp,
            segmentHorizontalLength = 14.dp,
            segmentThickness = 7.dp,
            bevel = 3.5.dp,
            onColor = Color(0xFF7B45FF),
            offColor = Color(0xFF332F21),
            alpha = 1f,
            glowRadius = 60f,
            flickerAmplitude = 0.25f,
            flickerFrequency = 1f,
            idleMode = false,
            idleSpeed = 100
        ),

    )


    val configSegments4 = iteratorConfigGenerator(
        sevenSegCfg = SevenSegmentConfig(onColor = Color(0xFF00B0FF),offColor = offCol,idleMode = false,idleSpeed = 100),
        nbrDigit = 9
    )

    val configSegments3 = iteratorConfigGenerator(
        sevenSegCfg = SevenSegmentConfig(onColor = Color(0xFFFF1744),offColor = offCol,idleMode = false,idleSpeed = 100),
        nbrDigit = 8
    )
    val configSegments2 = iteratorConfigGenerator(
        sevenSegCfg = SevenSegmentConfig(onColor = Color(0xFFFF9100),offColor = offCol),
        nbrDigit = 10
    )





    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().padding(top = 34.dp, bottom = 34.dp)) {

            Spacer(modifier = Modifier.height(40.dp))
            Multi7SegDisplay(configs = configSegments6,
                reflectConfig = reflectionConfig,
                overrideValue = "${DynamicDemoCompteurB*3}",
                reversedOverride = true,
                showZeroWhenEmpty = true,
                activateReflect = false,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                extraSpacingStep = 0,
                extraSpacing = 15.dp
            )

            Spacer(modifier = Modifier.height(40.dp))
            Multi7SegDisplay(configs = configSegments5,
                reflectConfig = reflectionConfig,
                overrideValue = "${DynamicDemoCompteurB}",
                reversedOverride = true,
                showZeroWhenEmpty = true,
                activateReflect = false,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                extraSpacingStep = 0,
                extraSpacing = 15.dp
            )

            Spacer(modifier = Modifier.height(40.dp))
            Multi7SegDisplay(configs = configSegments2,
                reflectConfig = reflectionConfig,
                overrideValue = "${DynamicDemoCompteur}",
                reversedOverride = false,
                showZeroWhenEmpty = false,
                activateReflect = false,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                extraSpacingStep = 0,
                extraSpacing = 15.dp
            )
            Spacer(modifier = Modifier.height(30.dp))
            Multi7SegDisplay(configs = configSegments3,
                reflectConfig = reflectionConfig,
                overrideValue = "89230856",
                reversedOverride = true,
                showZeroWhenEmpty = true,
                activateReflect = false,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                extraSpacingStep = 2,
                extraSpacing = 15.dp
            )
            Spacer(modifier = Modifier.height(30.dp))
            Multi7SegDisplay(configs = configSegments4,
                reflectConfig = reflectionConfig,
                overrideValue = "${DynamicDemoCompteur}",
                reversedOverride = true,
                showZeroWhenEmpty = true,
                activateReflect = false,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                extraSpacingStep = 3,
                extraSpacing = 30.dp
            )
            Spacer(modifier = Modifier.height(30.dp))
            Multi7SegDisplay(configs = configSegments,
                reflectConfig = reflectionConfig,
                overrideValue = "${DynamicDemoCompteur*7}",
                reversedOverride = true,
                showZeroWhenEmpty = false,
                activateReflect = true,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                extraSpacingStep = 0,
                extraSpacing = 15.dp
            )
            Spacer(modifier = Modifier.height(30.dp))

            FiveDigitDisplayWithReflect(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                digit1 = 2,
                digit2 = 3,
                digit3 = 4,
                digit4 = 5,
                digit5 = 6,
            )





        }
    }

}
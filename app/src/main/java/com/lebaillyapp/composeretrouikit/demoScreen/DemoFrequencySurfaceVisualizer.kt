package com.lebaillyapp.composeretrouikit.demoScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lebaillyapp.composeretrouikit.visualizer.RetroNeonFrequencySurface
import kotlin.math.roundToInt

@Composable
fun DemoRetroNeonFrequencySurfaceVisualizer(){

    var frequency by remember { mutableStateOf(440f) } // fréquence initiale en Hz
    var sigma by remember { mutableStateOf(3.5f) }
    var peakHeight by remember { mutableStateOf(20f) }
    var cellSize by remember { mutableStateOf(20f) }
    var cellHorizontalTotal by remember { mutableStateOf(20) }
    var cellVerticalTotal by remember { mutableStateOf(20) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF151313)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            //todo -=-=-=-=-=-=-=-=-=-=-=-=-=-=


            RetroNeonFrequencySurface(
                frequencyHz = frequency,
                sigma = sigma,
                peakHeight = peakHeight,
                gridCols = cellVerticalTotal,
                gridRows = cellHorizontalTotal,
                cellSize = cellSize,
                heatMapColor1 = Color.Cyan,
                heatMapColor2 = Color.Magenta,
                heatMapColor3 = Color.Red,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
            //todo -=-=-=-=-=-=-=-=-=-=-=-=-=-=
            Spacer(modifier = Modifier.height(84.dp))

            Text(
                text = "${frequency.toInt()} Hz",
                color = Color.DarkGray,
                style = MaterialTheme.typography.bodyLarge
            )

            Slider(
                value = frequency,
                onValueChange = { frequency = it.coerceIn(1f, 20000f) },
                valueRange = 1f..20000f,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Sigma: ${sigma.toInt()}",
                color = Color.DarkGray,
                style = MaterialTheme.typography.bodyLarge
            )
            Slider(
                value = sigma,
                onValueChange = { sigma = it },
                valueRange = 0.5f..20f,
                steps = 18
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Peak : ${peakHeight.toInt()}",
                color = Color.DarkGray,
                style = MaterialTheme.typography.bodyLarge
            )
            Slider(
                value = peakHeight,
                onValueChange = { peakHeight = it },
                valueRange = -1000f..1000f
            )
            Text(
                text = "Cell : ${cellSize.toInt()}",
                color = Color.DarkGray,
                style = MaterialTheme.typography.bodyLarge
            )
            Slider(
                value = cellSize,
                onValueChange = { cellSize = it },
                valueRange = 2f..100f
            )
            Slider(
                value = cellHorizontalTotal.toFloat(),
                onValueChange = { cellHorizontalTotal = it.roundToInt() },
                valueRange = 2f..100f
            )
            Slider(
                value = cellVerticalTotal.toFloat(),
                onValueChange = { cellVerticalTotal = it.roundToInt() },
                valueRange = 2f..100f
            )
        }
    }
}
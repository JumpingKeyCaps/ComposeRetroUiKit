package com.lebaillyapp.composeretrouikit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lebaillyapp.composeretrouikit.demoScreen.Demo14segmentDisplay
import com.lebaillyapp.composeretrouikit.demoScreen.DemoRetroNeonFrequencySurfaceVisualizer
import com.lebaillyapp.composeretrouikit.demoScreen.Demo_7segmentDisplay
import com.lebaillyapp.composeretrouikit.demoScreen.Demo_FractalVisualizer
import com.lebaillyapp.composeretrouikit.demoScreen.Demo_SimpleNeonOscilloscope
import com.lebaillyapp.composeretrouikit.ui.theme.ComposeRetroUIKitTheme
import com.lebaillyapp.composeretrouikit.visualizer.FractalVisualizer

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeRetroUIKitTheme {

                Box(modifier = Modifier.fillMaxSize().background(Color(0xFF090808)), contentAlignment = Alignment.Center){


                    //---- Demo Fractal visualizer
                    // Demo_FractalVisualizer(demoColor = Color(0xFF1DE9B6))

                    //----Demo Simple Neon Oscilloscope
             //       Demo_SimpleNeonOscilloscope()

                    //----Demo 7 segment display
           //         Demo_7segmentDisplay()

                    //----Demo retro neon frequency surface
                    DemoRetroNeonFrequencySurfaceVisualizer()

                    //----Demo 14segments
             //       Demo14segmentDisplay()

                }




            }
        }
    }
}

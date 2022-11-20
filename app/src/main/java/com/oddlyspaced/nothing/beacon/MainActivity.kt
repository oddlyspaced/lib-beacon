package com.oddlyspaced.nothing.beacon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oddlyspaced.nothing.beacon.lib.RootLedControllerImpl
import com.oddlyspaced.nothing.beacon.lib.constant.Section
import com.oddlyspaced.nothing.beacon.ui.Phone1
import com.oddlyspaced.nothing.beacon.ui.SectionToggle
import com.oddlyspaced.nothing.beacon.ui.theme.BeaconComposeTheme
import com.oddlyspaced.nothing.beacon.ui.theme.Main100
import com.smarttoolfactory.slider.ColorfulSlider

class MainActivity : ComponentActivity() {

    private val ledController = RootLedControllerImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BeaconComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Content()
                }
            }
        }
    }

    @Composable
    fun Heading() {
        Text(text = "Glyph Interface", fontSize = 44.sp, fontWeight = FontWeight.Medium)
    }

    @Composable
    fun Content() {
        var brightness by remember {
            mutableStateOf(0)
        }
        Column {
            Column(modifier = Modifier.padding(top = 80.dp, start = 16.dp, end = 16.dp)) {
                Heading()
                SectionToggle(
                    modifier = Modifier.padding(top = 24.dp),
                    text = "Glyph Lights",
                    defaultSwitchState = true
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth(0.5f),
                        color = Color.Transparent,
                    ) {
                        Phone1(
                            brightness,
                            brightness,
                            brightness,
                            brightness,
                            brightness
                        )
                    }
                }
                Text(
                    text = "Brightness",
                    color = Main100,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 16.dp)
                )
                ColorfulSlider(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(top = 4.dp),
                    value = brightness.toFloat(),
                    valueRange = 0f..4096f,
                    onValueChange = { value ->
                        brightness = value.toInt()
                    },
                    onValueChangeFinished = {
                        ledController.setSectionBrightness(Section.ALL_WHITE_LEDS, 0)
                    },
                    trackHeight = 24.dp,
                    thumbRadius = 0.dp,
                    steps = 1
                )
            }
        }
    }

    @Preview(showBackground = true, device = Devices.PIXEL_2_XL, showSystemUi = true)
    @Composable
    fun DefaultPreview() {
        BeaconComposeTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                Content()
            }
        }
    }
}
package com.oddlyspaced.nothing.beacon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrightnessHigh
import androidx.compose.material.icons.filled.BrightnessLow
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
import com.oddlyspaced.nothing.beacon.ui.SectionHeading
import com.oddlyspaced.nothing.beacon.ui.SectionToggle
import com.oddlyspaced.nothing.beacon.ui.theme.BeaconComposeTheme

class MainActivity : ComponentActivity() {

    private val ledController = RootLedControllerImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
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

    @Composable
    fun SectionTitle(modifier: Modifier, title: String, subtitle: String) {
        Column(modifier = modifier.fillMaxWidth()) {
            Text(title, fontSize = 18.sp, fontWeight = FontWeight.Medium)
            Text(subtitle, fontSize = 14.sp)
        }
    }

    @Composable
    fun Content() {
        var brightness by remember {
            mutableStateOf(0)
        }
        Column(Modifier.verticalScroll(rememberScrollState())) {
            Column(
                modifier = Modifier
                    .padding(top = 80.dp, start = 16.dp, end = 16.dp, bottom = 32.dp)
            ) {
                SectionHeading(text = "Glyph Interface")
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Filled.BrightnessLow, contentDescription = null)
                    Box(modifier = Modifier.fillMaxWidth(0.8f)) {
                        Slider(
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .padding(top = 4.dp),
                            value = brightness.toFloat(),
                            valueRange = 0f..4095f,
                            onValueChange = { value ->
                                brightness = value.toInt()
                                ledController.setSectionBrightness(Section.ALL_WHITE_LEDS, brightness)
                            },
                            onValueChangeFinished = {
                                ledController.setSectionBrightness(Section.ALL_WHITE_LEDS, 0)
                            },
                        )
                    }
                    Icon(Icons.Filled.BrightnessHigh, contentDescription = null)
                }
                SectionTitle(Modifier.padding(top = 16.dp), "Ringtones", "scribble")
                SectionTitle(Modifier.padding(top = 16.dp), "Notification sounds", "guiro")
//                Text(
//                    "Feedback",
//                    modifier = Modifier.padding(top = 32.dp),
//                    fontWeight = FontWeight.SemiBold,
//                    fontSize = 14.sp,
//                )
//                SectionTitle(Modifier.padding(top = 16.dp), "Charging meter", "On")
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
package com.oddlyspaced.nothing.beacon

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrightnessHigh
import androidx.compose.material.icons.filled.BrightnessLow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import com.oddlyspaced.nothing.beacon.lib.RootLedControllerImpl
import com.oddlyspaced.nothing.beacon.lib.constant.LedConstant
import com.oddlyspaced.nothing.beacon.lib.constant.Section
import com.oddlyspaced.nothing.beacon.ui.theme.*

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
    fun GlyphToggle() {
        val switchState = remember {
            mutableStateOf(true)
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 28.dp),
            backgroundColor = if (switchState.value) Main50 else Main10,
            shape = RoundedCornerShape(24.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 12.dp, bottom = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Glyph Lights", color = Main1000, fontSize = 20.sp)
                Switch(
                    checked = switchState.value, onCheckedChange = {
                        switchState.value = it
                    }, colors = SwitchDefaults.colors(
                        checkedThumbColor = Main1000,
                        uncheckedThumbColor = Main1000,
                        checkedTrackColor = Main200,
                        uncheckedTrackColor = Main800,
                    )
                )
            }
        }
    }

    private fun translateBrightnessToAlpha(brightness: Int): Float {
        (brightness / 4095f).let {
            return if (it < 0.1f) 0.1f else it
        }
    }

    @Composable
    fun PhoneLayout(
        cameraBrightness: Int,
        vlineBrightness: Int,
        centerBrightness: Int,
        bottomStripBrightness: Int,
        bottomDotBrightness: Int
    ) {
        Log.d("OMG", cameraBrightness.toString())
        Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(top = 24.dp)) {
            Image(
                painter = painterResource(id = com.oddlyspaced.nothing.beacon.lib.R.drawable.back),
                contentDescription = "",
            )
            Image(
                painter = painterResource(id = com.oddlyspaced.nothing.beacon.lib.R.drawable.led_strip_camera),
                contentDescription = "",
                alpha = translateBrightnessToAlpha(cameraBrightness)
            )
            Image(
                painter = painterResource(id = com.oddlyspaced.nothing.beacon.lib.R.drawable.led_strip_vline),
                contentDescription = "",
                alpha = translateBrightnessToAlpha(vlineBrightness)
            )
            Image(
                painter = painterResource(id = com.oddlyspaced.nothing.beacon.lib.R.drawable.led_strip_center),
                contentDescription = "",
                alpha = translateBrightnessToAlpha(centerBrightness)
            )
            Image(
                painter = painterResource(id = com.oddlyspaced.nothing.beacon.lib.R.drawable.led_strip_bottom),
                contentDescription = "",
                alpha = translateBrightnessToAlpha(bottomStripBrightness)
            )
            Image(
                painter = painterResource(id = com.oddlyspaced.nothing.beacon.lib.R.drawable.led_dot_bottom),
                contentDescription = "",
                alpha = translateBrightnessToAlpha(bottomDotBrightness)
            )
        }
    }

    @Composable
    fun BrightnessSlider(modifier: Modifier, onBrightnessUpdate: (Int) -> Unit) {
        var sliderPosition by remember { mutableStateOf(0f) }
        Row(
            modifier,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Filled.BrightnessLow, contentDescription = null)
            Slider(
                value = sliderPosition,
                onValueChange = {
                    sliderPosition = it
                    onBrightnessUpdate((sliderPosition * LedConstant.BRIGHTNESS_MAX).toInt())
                },
                modifier = Modifier.fillMaxWidth(0.8f),
                onValueChangeFinished = {
                    ledController.setSectionBrightness(Section.ALL_WHITE_LEDS, 0)
                }
            )
            Icon(Icons.Filled.BrightnessHigh, contentDescription = null)
        }
    }

    @Composable
    fun Content() {
        var brightness by remember {
            mutableStateOf(0)
        }
        Column {
            Column(modifier = Modifier.padding(top = 80.dp, start = 16.dp, end = 16.dp)) {
                Heading()
                GlyphToggle()
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth(0.5f),
                        color = Color.Transparent,
                    ) {
                        PhoneLayout(
                            brightness,
                            brightness,
                            brightness,
                            brightness,
                            brightness
                        )
                    }
                }
                BrightnessSlider(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth(),
                ) { br ->
                    ledController.setSectionBrightness(Section.ALL_WHITE_LEDS, br)
                    brightness = br
                }

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
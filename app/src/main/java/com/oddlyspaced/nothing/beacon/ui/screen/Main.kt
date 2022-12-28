package com.oddlyspaced.nothing.beacon.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrightnessHigh
import androidx.compose.material.icons.filled.BrightnessLow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.oddlyspaced.nothing.beacon.lib.RootLedControllerImpl
import com.oddlyspaced.nothing.beacon.lib.constant.Section
import com.oddlyspaced.nothing.beacon.navigation.Screens
import com.oddlyspaced.nothing.beacon.ui.component.PageHeading
import com.oddlyspaced.nothing.beacon.ui.component.Phone1
import com.oddlyspaced.nothing.beacon.ui.component.SectionItem
import com.oddlyspaced.nothing.beacon.ui.component.SectionToggle


@Composable
fun Main(navController: NavController) {
    val ledController = RootLedControllerImpl()
    var brightness by remember {
        mutableStateOf(0)
    }
    Column(Modifier.verticalScroll(rememberScrollState())) {
        Column(
            modifier = Modifier
                .padding(top = 80.dp, start = 16.dp, end = 16.dp, bottom = 32.dp)
        ) {
            PageHeading(text = "Glyph Interface")
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
            SectionItem(
                Modifier.padding(top = 16.dp), "Ringtones", "scribble"
            ) { navController.navigate(Screens.RingtoneSelection.route) }
            SectionItem(Modifier.padding(top = 16.dp), "Notification sounds", "guiro") {
                navController.navigate(Screens.RingtoneSelection.route)
            }
        }
    }
}
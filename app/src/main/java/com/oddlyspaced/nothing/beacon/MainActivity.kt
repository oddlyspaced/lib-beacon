package com.oddlyspaced.nothing.beacon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Device
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oddlyspaced.nothing.beacon.ui.theme.*

class MainActivity : ComponentActivity() {
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
        modifier = Modifier.fillMaxWidth().padding(top = 28.dp),
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

@Composable
fun Content() {
    Column() {
        Column(modifier = Modifier.padding(top = 80.dp, start = 16.dp, end = 16.dp)) {
            Heading()
            GlyphToggle()
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
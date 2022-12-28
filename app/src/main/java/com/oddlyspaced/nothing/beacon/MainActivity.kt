package com.oddlyspaced.nothing.beacon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.oddlyspaced.nothing.beacon.navigation.NavGraph
import com.oddlyspaced.nothing.beacon.service.LedHandlerService
import com.oddlyspaced.nothing.beacon.ui.theme.BeaconComposeTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LedHandlerService.start(applicationContext)
        setContent {
            BeaconComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavGraph(navController = navController)
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
//                Content()
            }
        }
    }
}
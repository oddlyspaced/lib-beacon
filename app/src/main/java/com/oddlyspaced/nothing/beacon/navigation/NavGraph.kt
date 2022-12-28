package com.oddlyspaced.nothing.beacon.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.oddlyspaced.nothing.beacon.ui.screen.Main
import com.oddlyspaced.nothing.beacon.ui.MediaSelector

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.Main.route
    )
    {
        composable(route = Screens.Main.route) {
            Main(navController)
        }
        composable(route = Screens.RingtoneSelection.route) {
            MediaSelector()
        }
        composable(route = Screens.NotificationSelection.route) {
            MediaSelector()
        }
    }
}
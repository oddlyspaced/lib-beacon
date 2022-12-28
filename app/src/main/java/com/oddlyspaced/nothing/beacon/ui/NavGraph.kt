package com.oddlyspaced.nothing.beacon.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.Main.route
    )
    {
        composable(route = Screens.Main.route) {
            Content(navController)
        }
        composable(route = Screens.RingtoneSelection.route) {
            MediaSelector()
        }
        composable(route = Screens.NotificationSelection.route) {
            MediaSelector()
        }
    }
}
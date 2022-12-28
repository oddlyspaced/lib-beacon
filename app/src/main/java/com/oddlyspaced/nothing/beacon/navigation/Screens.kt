package com.oddlyspaced.nothing.beacon.navigation

sealed class Screens(val route: String) {
    object Main: Screens("main")
    object RingtoneSelection: Screens("ringtone_section")
    object NotificationSelection: Screens("notification_selection")
}
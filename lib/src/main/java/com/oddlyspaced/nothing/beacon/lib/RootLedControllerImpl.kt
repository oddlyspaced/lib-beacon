package com.oddlyspaced.nothing.beacon.lib

import com.oddlyspaced.nothing.beacon.lib.enum.LedConstant.LED_DRIVER_PATH
import com.oddlyspaced.nothing.beacon.lib.enum.LedConstant.NODE_SINGLE_BRIGHTNESS
import com.oddlyspaced.nothing.beacon.lib.enum.Led
import com.oddlyspaced.nothing.beacon.lib.enum.Section
import com.oddlyspaced.nothing.beacon.lib.interfaces.LedController
import com.topjohnwu.superuser.Shell

/**
 * Magisk based implementation for LED control
 */
class RootLedControllerImpl : LedController {
    override fun setLedBrightness(led: Led, brightness: Int) {
        validateBrightness(brightness)
        Shell.cmd("echo ${led.code} $brightness > ${LED_DRIVER_PATH}/${NODE_SINGLE_BRIGHTNESS}").exec()
    }

    override fun setSectionBrightness(section: Section, brightness: Int) {
        validateBrightness(brightness)
        Shell.cmd("echo $brightness > ${LED_DRIVER_PATH}/${section.section}").exec()
    }
}
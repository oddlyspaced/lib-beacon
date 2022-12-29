package com.oddlyspaced.nothing.beacon.lib.interfaces

import com.oddlyspaced.nothing.beacon.lib.enum.Led
import com.oddlyspaced.nothing.beacon.lib.enum.LedConstant
import com.oddlyspaced.nothing.beacon.lib.enum.Section

/**
 * Interface for controlling LEDs
 */
interface LedController {
    /**
     * Validate brightness range
     * @param brightness brightness value to set. must be in range
     */
    fun validateBrightness(brightness: Int) {
        if (brightness > LedConstant.BRIGHTNESS_MAX || brightness < LedConstant.BRIGHTNESS_MIN) {
            throw Exception("Brightness $brightness is out of range! [Must be within ${LedConstant.BRIGHTNESS_MIN} - ${LedConstant.BRIGHTNESS_MAX}")
        }
    }

    /**
     * Sets LED brightness
     * @param led led code
     * @param brightness intensity
     */
    fun setLedBrightness(led: Led, brightness: Int)

    /**
     * Sets Section brightness
     * @param section section node name
     * @param brightness intensity
     */
    fun setSectionBrightness(section: Section, brightness: Int)

    /**
     * Sets all LEDs to 0
     */
    fun resetAll() {
        setSectionBrightness(Section.ALL_WHITE_LEDS, 0)
    }
}
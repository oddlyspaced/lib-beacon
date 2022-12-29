package com.oddlyspaced.nothing.beacon.lib.enum

enum class Led(val code: Int) {
    // Slant Strip
    LED_SLANT_STRIP(1),
    // Center Circle
    LED_CIRCLE_BOTTOM_LEFT(2),
    LED_CIRCLE_BOTTOM_RIGHT(3),
    LED_CIRCLE_TOP_LEFT(5),
    LED_CIRCLE_TOP_RIGHT(4),
    // Camera Ring
    LED_CAMERA_RING(7),
    // Battery Bar
    LED_BATTERY_0(8),
    LED_BATTERY_1(15),
    LED_BATTERY_2(14),
    LED_BATTERY_3(10),
    LED_BATTERY_4(12),
    LED_BATTERY_5(9),
    LED_BATTERY_6(11),
    LED_BATTERY_7(13),
    // Battery Dot
    LED_BATTERY_DOT(16),
    // Red Recording Indicator
    LED_RECORDING_INDICATOR(17);

    companion object  {
        fun fromCode(value: Int) = values().first { it.code == value }
    }
}
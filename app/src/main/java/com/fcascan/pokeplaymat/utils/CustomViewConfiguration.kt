package com.fcascan.pokeplaymat.utils

import androidx.compose.ui.platform.ViewConfiguration
import androidx.compose.ui.unit.Density

/**
 * Custom view configuration to set custom values for the view configuration properties.
 */
class CustomViewConfiguration(density: Density) : ViewConfiguration {
    /**
     * The duration between the first tap's up event and the second tap's down event for an interaction to be considered a double-tap.
     */
    override val doubleTapMinTimeMillis: Long
        get() = 40 //40

    /**
     * The duration between the first tap's up event and the second tap's down event for an interaction to be considered a double-tap.
     */
    override val doubleTapTimeoutMillis: Long
        get() = 300 //300

    /**
     * The duration before a press turns into a long press.
     */
    override val longPressTimeoutMillis: Long
        get() = 500 //500

    /**
     * Distance in pixels a touch can wander before we think the user is scrolling.
     */
    override val touchSlop: Float
        get() = 23f //23f
}
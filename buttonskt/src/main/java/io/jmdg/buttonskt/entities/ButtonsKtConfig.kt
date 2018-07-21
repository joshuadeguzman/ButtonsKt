package io.jmdg.buttonskt.entities

import android.graphics.Color

/**
 * Created by Joshua de Guzman on 21/07/2018.
 */

data class ButtonsKtConfig(
        // Default configurations
        internal var isEnabled: Boolean = true,
        internal var isRippleEffectEnabled: Boolean = true,

        // Text
        internal var text: String = "ButtonsKt",
        internal var textSize: Float = 16f,
        internal var textColor: Int = Color.parseColor("#ffffff"),
        internal var disabledTextColor: Int = Color.parseColor("#ffffff"),
        internal var textGravity: Int = 1,
        internal var textAlignment: Int = 4,
        internal var isTextAllCaps: Boolean = false,
        internal var textStyle: Int = 0,

        // Background
        internal var defaultBackgroundColor: Int = Color.parseColor("#2c3e50"),
        internal var focusedBackgroundColor: Int = Color.parseColor("#34495e"),
        internal var disabledBackgroundColor: Int = Color.parseColor("#ecf0f1"),

        // Shape
        internal var radius: Float = 0f,
        internal var radiusTopLeft: Float = 0f,
        internal var radiusTopRight: Float = 0f,
        internal var radiusBottomLeft: Float = 0f,
        internal var radiusBottomRight: Float = 0f
)
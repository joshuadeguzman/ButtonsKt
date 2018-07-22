package io.jmdg.buttonskt.entities

import android.graphics.Color
import io.jmdg.buttonskt.constants.BktLayoutParams

/**
 * Created by Joshua de Guzman on 21/07/2018.
 */

data class ButtonsKt(
        // Default configurations
        internal var isEnabled: Boolean = true,
        internal var isRippleEffectEnabled: Boolean = true,

        // Text
        internal var text: String = "ButtonsKtView",
        internal var textSize: Float = 16f,
        internal var textColor: Int = Color.parseColor("#ffffff"),
        internal var disabledTextColor: Int = Color.parseColor("#ffffff"),
        internal var textGravity: Int = 1,
        internal var textPosition: Int = 13,
        internal var isTextAllCaps: Boolean = false,
        internal var textStyle: Int = 0,

        // Layout Control
        internal var width: Int = BktLayoutParams.WRAP_CONTENT,
        internal var height: Int = BktLayoutParams.WRAP_CONTENT,

        // Background
        internal var defaultBackgroundColor: Int = Color.parseColor("#2c3e50"),
        internal var focusedBackgroundColor: Int = Color.parseColor("#34495e"),
        internal var disabledBackgroundColor: Int = Color.parseColor("#ecf0f1"),

        // Border
        internal var borderWidth: Int = 0,
        internal var defaultBorderColor: Int = Color.parseColor("#34495e"),
        internal var focusedBorderColor: Int = Color.TRANSPARENT,
        internal var disabledBorderColor: Int = Color.TRANSPARENT,

        // Margin
        internal var margin: Int = 0,
        internal var marginLeft: Int = 0,
        internal var marginTop: Int = 0,
        internal var marginRight: Int = 0,
        internal var marginBottom: Int = 0,

        // Padding
        internal var padding: Int = 50,
        internal var paddingLeft: Int = 50,
        internal var paddingTop: Int = 50,
        internal var paddingRight: Int = 50,
        internal var paddingBottom: Int = 50,

        // Radius
        internal var radius: Float = 0f,
        internal var radiusTopLeft: Float = 0f,
        internal var radiusTopRight: Float = 0f,
        internal var radiusBottomLeft: Float = 0f,
        internal var radiusBottomRight: Float = 0f
)
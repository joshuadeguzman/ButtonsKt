package io.jmdg.buttonskt.entities

import android.graphics.Color

/**
 * Created by Joshua de Guzman on 21/07/2018.
 */

data class ButtonsKtConfig(
        internal var defaultBackgroundColor: Int = Color.parseColor("#2c3e50"),
        internal var focusedBackgroundColor: Int = Color.parseColor("#34495e"),
        internal var disabledBackgroundColor: Int = Color.parseColor("#ecf0f1"),
        internal var isEnabled: Boolean = true,
        internal var isRippleEffectEnabled: Boolean = true,
        internal var radius: Float = 0f,
        internal var radiusTopLeft: Float = 0f,
        internal var radiusTopRight: Float = 0f,
        internal var radiusBottomLeft: Float = 0f,
        internal var radiusBottomRight: Float = 0f
)
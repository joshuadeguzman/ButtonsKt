package io.jmdg.buttonskt.entities

import android.graphics.Color
import android.view.Gravity
import io.jmdg.buttonskt.constants.BktLayoutParams
import io.jmdg.buttonskt.constants.BktTextPosition

/**
 * Created by Joshua de Guzman on 21/07/2018.
 */

data class ButtonsKt(
        // Default configurations
        internal var isEnabled: Boolean = true,
        internal var isRippleEffectEnabled: Boolean = true,

        // Icon
        internal var iconResource: Int = -1,
        internal var iconDrawable: Int = -1,
        internal var iconTint: Int = Color.WHITE,
        internal var disabledIconTint: Int = Color.TRANSPARENT,
        internal var iconArea: Float = 25f,

        // Icon Margin
        internal var iconMargin: Int = 0,
        internal var iconMarginLeft: Int = 0,
        internal var iconMarginTop: Int = 10,
        internal var iconMarginRight: Int = 20,
        internal var iconMarginBottom: Int = 10,

        // Icon Padding
        internal var iconPadding: Int = 0,
        internal var iconPaddingLeft: Int = 0,
        internal var iconPaddingTop: Int = 0,
        internal var iconPaddingRight: Int = 0,
        internal var iconPaddingBottom: Int = 0,

        // Text
        internal var text: String = "ButtonsKtView",
        internal var textSize: Float = 15f,
        internal var textColor: Int = Color.parseColor("#ffffff"),
        internal var disabledTextColor: Int = Color.parseColor("#ffffff"),
        internal var textGravity: Int = Gravity.CENTER,
        internal var textPosition: Int = BktTextPosition.CENTER,
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
        internal var padding: Int = 30,
        internal var paddingLeft: Int = 30,
        internal var paddingTop: Int = 30,
        internal var paddingRight: Int = 30,
        internal var paddingBottom: Int = 30,

        // Radius
        internal var radius: Float = 0f,
        internal var radiusTopLeft: Float = 0f,
        internal var radiusTopRight: Float = 0f,
        internal var radiusBottomLeft: Float = 0f,
        internal var radiusBottomRight: Float = 0f
)
package io.jmdg.buttonskt

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.widget.LinearLayout
import io.jmdg.buttonskt.entities.ButtonsKtConfig
import android.content.res.ColorStateList
import android.graphics.drawable.RippleDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.annotation.TargetApi
import android.graphics.drawable.StateListDrawable


/**
 * Created by Joshua de Guzman on 21/07/2018.
 */

class ButtonsKt(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    private val tag = ButtonsKt::class.java.simpleName

    companion object {
        val config = ButtonsKtConfig()

        fun defaultConfig(init: ButtonsKtConfig.() -> Unit) {
            config.init()
        }
    }

    init {
        initButtonAttributes()

        val typedArray: TypedArray = context!!.obtainStyledAttributes(attrs, R.styleable.ButtonsKt, 0, 0)
        loadAttributes(typedArray)
        typedArray.recycle()
    }

    private fun initButtonAttributes() {
        this.isClickable = true
        this.isFocusable = true
    }

    private fun loadAttributes(typedArray: TypedArray) {

        // Override default configurations when attributes is set via XML
        defaultConfig {
            defaultBackgroundColor = typedArray.getColor(R.styleable.ButtonsKt_bkt_defaultBackgroundColor, config.defaultBackgroundColor)
            focusedBackgroundColor = typedArray.getColor(R.styleable.ButtonsKt_bkt_focusedBackgroundColor, config.focusedBackgroundColor)
            disabledBackgroundColor = typedArray.getColor(R.styleable.ButtonsKt_bkt_disabledBackgroundColor, config.disabledBackgroundColor)
            defaultBackgroundColor = typedArray.getColor(R.styleable.ButtonsKt_bkt_defaultBackgroundColor, config.disabledBackgroundColor)
            radius = typedArray.getDimension(R.styleable.ButtonsKt_bkt_radius, config.radius)
            radiusTopLeft = typedArray.getDimension(R.styleable.ButtonsKt_bkt_radiusTopLeft, config.radiusTopLeft)
            radiusTopRight = typedArray.getDimension(R.styleable.ButtonsKt_bkt_radiusTopRight, config.radiusTopRight)
            radiusBottomLeft = typedArray.getDimension(R.styleable.ButtonsKt_bkt_radiusBottomLeft, config.radiusBottomLeft)
            radiusBottomRight = typedArray.getDimension(R.styleable.ButtonsKt_bkt_radiusBottomRight, config.radiusBottomRight)
        }

        renderBackground()
    }

    private fun renderBackground() {
        val defaultDrawable = GradientDrawable()
        val focusedDrawable = GradientDrawable()
        val disabledDrawable = GradientDrawable()

        renderRadius(defaultDrawable)
        renderRadius(focusedDrawable)
        renderRadius(disabledDrawable)

        defaultDrawable.setColor(config.defaultBackgroundColor)
        focusedDrawable.setColor(config.focusedBackgroundColor)
        disabledDrawable.setColor(config.disabledBackgroundColor)

        renderBackground(defaultDrawable, focusedDrawable, disabledDrawable)
    }

    private fun renderBackground(defaultDrawable: GradientDrawable, focusedDrawable: GradientDrawable, disabledDrawable: GradientDrawable) {
        if (config.isRippleEffectEnabled && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.background = getBackgroundWithRipple(defaultDrawable, focusedDrawable, disabledDrawable)
        } else {
            val stateListDrawable = StateListDrawable()
            stateListDrawable.addState(intArrayOf(android.R.attr.state_pressed), focusedDrawable)
            stateListDrawable.addState(intArrayOf(android.R.attr.state_focused), focusedDrawable)
            stateListDrawable.addState(intArrayOf(-android.R.attr.state_enabled), disabledDrawable)
            stateListDrawable.addState(intArrayOf(), defaultDrawable)
            this.background = stateListDrawable
        }
    }

    private fun renderRadius(gradientDrawable: GradientDrawable) {
        if (config.radius > 0) {
            gradientDrawable.cornerRadius = config.radius
        } else {
            gradientDrawable.cornerRadii = floatArrayOf(
                    config.radiusTopLeft, config.radiusTopLeft,
                    config.radiusTopRight, config.radiusTopRight,
                    config.radiusBottomRight, config.radiusBottomRight,
                    config.radiusBottomLeft, config.radiusBottomLeft)
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun getBackgroundWithRipple(defaultDrawable: Drawable, focusDrawable: Drawable, disabledDrawable: Drawable): Drawable {
        return if (!config.isEnabled) {
            disabledDrawable
        } else {
            RippleDrawable(ColorStateList.valueOf(config.focusedBackgroundColor), defaultDrawable, focusDrawable)
        }
    }
}
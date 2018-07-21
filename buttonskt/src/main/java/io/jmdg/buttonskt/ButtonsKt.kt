package io.jmdg.buttonskt

import android.annotation.SuppressLint
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
import android.widget.TextView


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
            // Configuration
            isEnabled = typedArray.getBoolean(R.styleable.ButtonsKt_bkt_isEnabled, true)
            isRippleEffectEnabled = typedArray.getBoolean(R.styleable.ButtonsKt_bkt_isRippleEffectEnabled, true)

            // Text
            text = if (typedArray.getString(R.styleable.ButtonsKt_bkt_text) != null) {
                typedArray.getString(R.styleable.ButtonsKt_bkt_text)
            } else {
                tag
            }

            textSize = typedArray.getDimension(R.styleable.ButtonsKt_bkt_textSize, config.textSize)
            textColor = typedArray.getColor(R.styleable.ButtonsKt_bkt_textColor, config.textColor)
            disabledTextColor = typedArray.getColor(R.styleable.ButtonsKt_bkt_disabledTextColor, config.disabledTextColor)
            textGravity = typedArray.getInteger(R.styleable.ButtonsKt_bkt_textGravity, 1)
            textAlignment = typedArray.getInteger(R.styleable.ButtonsKt_bkt_textAlignment, 4)
            isTextAllCaps = typedArray.getBoolean(R.styleable.ButtonsKt_bkt_textAllCaps, false)
            textStyle = typedArray.getInteger(R.styleable.ButtonsKt_bkt_textStyle, 0)

            // Background
            defaultBackgroundColor = typedArray.getColor(R.styleable.ButtonsKt_bkt_defaultBackgroundColor, config.defaultBackgroundColor)
            focusedBackgroundColor = typedArray.getColor(R.styleable.ButtonsKt_bkt_focusedBackgroundColor, config.focusedBackgroundColor)
            disabledBackgroundColor = typedArray.getColor(R.styleable.ButtonsKt_bkt_disabledBackgroundColor, config.disabledBackgroundColor)
            defaultBackgroundColor = typedArray.getColor(R.styleable.ButtonsKt_bkt_defaultBackgroundColor, config.disabledBackgroundColor)

            // Shape
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
        renderSubViews()
    }

    private fun renderSubViews() {
        renderTextView()
    }

    @SuppressLint("WrongConstant")
    private fun renderTextView() {
        if (config.isTextAllCaps) {
            config.text = config.text.toUpperCase()
        }

        val textView = TextView(context)
        textView.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        textView.text = config.text
        textView.textSize = config.textSize
        textView.setTextColor(config.textColor)
        textView.gravity = config.textGravity
        textView.setTypeface(textView.typeface, config.textStyle)

        if (!config.isEnabled) {
            textView.setTextColor(config.disabledTextColor)
        }

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
            textView.textAlignment = config.textAlignment
        }

        this.addView(textView)
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
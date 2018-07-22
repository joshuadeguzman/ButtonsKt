package io.jmdg.buttonskt

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import io.jmdg.buttonskt.entities.ButtonsKt
import android.content.res.ColorStateList
import android.graphics.drawable.RippleDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.annotation.TargetApi
import android.graphics.drawable.StateListDrawable
import android.util.Log
import android.widget.RelativeLayout
import android.widget.TextView
import io.jmdg.buttonskt.constants.BktTextPosition


/**
 * Created by Joshua de Guzman on 21/07/2018.
 */

class ButtonsKtView : RelativeLayout {
    private val tag = ButtonsKtView::class.java.simpleName
    private var attrs: AttributeSet? = null
    private var config = ButtonsKt()

    constructor(context: Context, buttonsKtConfig: ButtonsKt) : super(context) {
        this.config = buttonsKtConfig
        Log.e(tag, this.config.text)
        initButtonAttributes()
        initBuilderAttributes()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        this.attrs = attrs
        initButtonAttributes()
        initXMLAttributes()
    }

    private fun defaultConfig(init: ButtonsKt.() -> Unit) {
        config.init()
    }

    private fun initBuilderAttributes() {
        renderBuilderConfigurations()
        renderBackground()
    }

    private fun initXMLAttributes() {
        val typedArray: TypedArray = context!!.obtainStyledAttributes(attrs, R.styleable.ButtonsKtView, 0, 0)
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
            isEnabled = typedArray.getBoolean(R.styleable.ButtonsKtView_bkt_isEnabled, true)
            isRippleEffectEnabled = typedArray.getBoolean(R.styleable.ButtonsKtView_bkt_isRippleEffectEnabled, true)

            // Text
            text = if (typedArray.getString(R.styleable.ButtonsKtView_bkt_text) != null) {
                typedArray.getString(R.styleable.ButtonsKtView_bkt_text)
            } else {
                tag
            }

            textSize = typedArray.getDimension(R.styleable.ButtonsKtView_bkt_textSize, config.textSize)
            textColor = typedArray.getColor(R.styleable.ButtonsKtView_bkt_textColor, config.textColor)
            disabledTextColor = typedArray.getColor(R.styleable.ButtonsKtView_bkt_disabledTextColor, config.disabledTextColor)
            textGravity = typedArray.getInteger(R.styleable.ButtonsKtView_bkt_textGravity, config.textGravity)
            textPosition = typedArray.getInteger(R.styleable.ButtonsKtView_bkt_textPosition, config.textPosition)
            isTextAllCaps = typedArray.getBoolean(R.styleable.ButtonsKtView_bkt_textAllCaps, false)
            textStyle = typedArray.getInteger(R.styleable.ButtonsKtView_bkt_textStyle, config.textStyle)

            // Background
            defaultBackgroundColor = typedArray.getColor(R.styleable.ButtonsKtView_bkt_defaultBackgroundColor, config.defaultBackgroundColor)
            focusedBackgroundColor = typedArray.getColor(R.styleable.ButtonsKtView_bkt_focusedBackgroundColor, config.focusedBackgroundColor)
            disabledBackgroundColor = typedArray.getColor(R.styleable.ButtonsKtView_bkt_disabledBackgroundColor, config.disabledBackgroundColor)
            defaultBackgroundColor = typedArray.getColor(R.styleable.ButtonsKtView_bkt_defaultBackgroundColor, config.disabledBackgroundColor)

            // Shape
            radius = typedArray.getDimension(R.styleable.ButtonsKtView_bkt_radius, config.radius)
            radiusTopLeft = typedArray.getDimension(R.styleable.ButtonsKtView_bkt_radiusTopLeft, config.radiusTopLeft)
            radiusTopRight = typedArray.getDimension(R.styleable.ButtonsKtView_bkt_radiusTopRight, config.radiusTopRight)
            radiusBottomLeft = typedArray.getDimension(R.styleable.ButtonsKtView_bkt_radiusBottomLeft, config.radiusBottomLeft)
            radiusBottomRight = typedArray.getDimension(R.styleable.ButtonsKtView_bkt_radiusBottomRight, config.radiusBottomRight)
        }

        renderBackground()
    }

    private fun renderBuilderConfigurations() {
        // Override layoutParams
        val layoutParams = RelativeLayout.LayoutParams(config.width,config.height)

        // Margin
        if (config.margin > 0) {
            layoutParams.setMargins(config.margin, config.margin, config.margin, config.margin)
        } else {
            layoutParams.setMargins(config.marginLeft, config.marginTop, config.marginRight, config.marginBottom)
        }

        // Padding
        if (config.padding == -1) {
            config.padding = resources.getDimension(R.dimen.padding_normal).toInt()
        }

        if (config.paddingLeft == -1) {
            config.paddingLeft = resources.getDimension(R.dimen.padding_normal).toInt()
        }

        if (config.paddingTop == -1) {
            config.paddingTop = resources.getDimension(R.dimen.padding_normal).toInt()
        }

        if (config.paddingRight == -1) {
            config.paddingRight = resources.getDimension(R.dimen.padding_normal).toInt()
        }

        if (config.paddingBottom == -1) {
            config.paddingBottom = resources.getDimension(R.dimen.padding_normal).toInt()
        }

        if (config.padding > 0) {
            this.setPadding(config.padding, config.padding, config.padding, config.padding)
        } else {
            this.setPadding(config.paddingLeft, config.paddingTop, config.paddingRight, config.paddingBottom)
        }

        // Render set layoutParams
        this.layoutParams = layoutParams
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

    private fun renderTextView() {
        if (config.isTextAllCaps) {
            config.text = config.text.toUpperCase()
        }

        val layoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
        layoutParams.addRule(config.textPosition)

        if (config.textPosition == BktTextPosition.LEFT || config.textPosition == BktTextPosition.RIGHT) {
            layoutParams.addRule(RelativeLayout.CENTER_VERTICAL)
        } else if (config.textPosition == BktTextPosition.TOP || config.textPosition == BktTextPosition.BOTTOM) {
            layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL)
        }

        val textView = TextView(context)
        textView.layoutParams = layoutParams
        textView.text = config.text
        textView.textSize = config.textSize
        textView.setTextColor(config.textColor)
        textView.gravity = config.textGravity
        textView.setTypeface(textView.typeface, config.textStyle)

        if (!config.isEnabled) {
            textView.setTextColor(config.disabledTextColor)
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
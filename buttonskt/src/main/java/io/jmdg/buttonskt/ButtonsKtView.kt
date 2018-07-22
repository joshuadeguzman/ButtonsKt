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
import android.graphics.Color
import android.graphics.drawable.StateListDrawable
import android.util.TypedValue
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import io.jmdg.buttonskt.internal.helpers.ResolutionUtil
import android.graphics.Typeface
import android.util.Log
import android.view.Gravity
import io.jmdg.buttonskt.internal.ButtonKtSingleton


/**
 * Created by Joshua de Guzman on 21/07/2018.
 */

class ButtonsKtView : LinearLayout {
    private val tag = ButtonsKtView::class.java.simpleName
    private var attrs: AttributeSet? = null
    private var config = ButtonsKt()

    constructor(context: Context, buttonsKtConfig: ButtonsKt) : super(context) {
        this.config = buttonsKtConfig
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

        renderXMLBuilderConfigurations()
    }

    private fun initButtonAttributes() {
        this.isClickable = true
        this.isFocusable = true
    }

    private fun renderXMLBuilderConfigurations() {
        // Override padding
        if (config.padding > 0) {
            this.setPadding(config.padding, config.padding, config.padding, config.padding)
        } else {
            this.setPadding(config.paddingLeft, config.paddingTop, config.paddingRight, config.paddingBottom)
        }
    }

    private fun loadAttributes(typedArray: TypedArray) {
        // Override default configurations when attributes is set via XML
        defaultConfig {
            // Configuration
            isEnabled = typedArray.getBoolean(R.styleable.ButtonsKtView_bkt_isEnabled, true)
            isRippleEffectEnabled = typedArray.getBoolean(R.styleable.ButtonsKtView_bkt_isRippleEffectEnabled, true)

            // Icon Resource
            iconResource = typedArray.getInteger(R.styleable.ButtonsKtView_bkt_iconResource, -1)
            iconDrawable = typedArray.getResourceId(R.styleable.ButtonsKtView_bkt_iconDrawable, -1)
            iconTint = typedArray.getColor(R.styleable.ButtonsKtView_bkt_iconTint, config.iconTint)
            iconArea = typedArray.getDimension(R.styleable.ButtonsKtView_bkt_iconArea, config.iconArea)

            // Icon Margin
            iconMargin = typedArray.getDimension(R.styleable.ButtonsKtView_bkt_iconMargin, config.iconMargin.toFloat()).toInt()
            iconMarginLeft = typedArray.getDimension(R.styleable.ButtonsKtView_bkt_iconMarginLeft, config.iconMarginLeft.toFloat()).toInt()
            iconMarginTop = typedArray.getDimension(R.styleable.ButtonsKtView_bkt_iconMarginTop, config.iconMarginTop.toFloat()).toInt()
            iconMarginRight = typedArray.getDimension(R.styleable.ButtonsKtView_bkt_iconMarginRight, config.iconMarginRight.toFloat()).toInt()
            iconMarginBottom = typedArray.getDimension(R.styleable.ButtonsKtView_bkt_iconMarginBottom, config.iconMarginBottom.toFloat()).toInt()

            // Icon Padding
            iconPadding = typedArray.getDimension(R.styleable.ButtonsKtView_bkt_iconPadding, config.iconPadding.toFloat()).toInt()
            iconPaddingLeft = typedArray.getDimension(R.styleable.ButtonsKtView_bkt_iconPaddingLeft, config.iconPaddingLeft.toFloat()).toInt()
            iconPaddingTop = typedArray.getDimension(R.styleable.ButtonsKtView_bkt_iconPaddingTop, config.iconPaddingTop.toFloat()).toInt()
            iconPaddingRight = typedArray.getDimension(R.styleable.ButtonsKtView_bkt_iconPaddingRight, config.iconPaddingRight.toFloat()).toInt()
            iconPaddingBottom = typedArray.getDimension(R.styleable.ButtonsKtView_bkt_iconPaddingBottom, config.iconPaddingBottom.toFloat()).toInt()

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

            // Border
            borderWidth = typedArray.getDimension(R.styleable.ButtonsKtView_bkt_borderWidth, config.borderWidth.toFloat()).toInt()
            defaultBorderColor = typedArray.getColor(R.styleable.ButtonsKtView_bkt_defaultBorderColor, config.defaultBorderColor)
            focusedBorderColor = typedArray.getColor(R.styleable.ButtonsKtView_bkt_focusedBorderColor, config.focusedBorderColor)
            disabledBorderColor = typedArray.getColor(R.styleable.ButtonsKtView_bkt_disabledBorderColor, config.disabledBorderColor)

            // Padding
            padding = typedArray.getDimension(R.styleable.ButtonsKtView_bkt_padding, config.padding.toFloat()).toInt()
            paddingLeft = typedArray.getDimension(R.styleable.ButtonsKtView_bkt_paddingLeft, config.paddingLeft.toFloat()).toInt()
            paddingTop = typedArray.getDimension(R.styleable.ButtonsKtView_bkt_paddingTop, config.paddingTop.toFloat()).toInt()
            paddingRight = typedArray.getDimension(R.styleable.ButtonsKtView_bkt_paddingRight, config.paddingRight.toFloat()).toInt()
            paddingBottom = typedArray.getDimension(R.styleable.ButtonsKtView_bkt_paddingBottom, config.paddingBottom.toFloat()).toInt()

            // Radius
            radius = typedArray.getDimension(R.styleable.ButtonsKtView_bkt_radius, ResolutionUtil.dpToPx(context, config.radius).toFloat())
            radiusTopLeft = typedArray.getDimension(R.styleable.ButtonsKtView_bkt_radiusTopLeft, ResolutionUtil.dpToPx(context, config.radiusTopLeft).toFloat())
            radiusTopRight = typedArray.getDimension(R.styleable.ButtonsKtView_bkt_radiusTopRight, ResolutionUtil.dpToPx(context, config.radiusTopRight).toFloat())
            radiusBottomLeft = typedArray.getDimension(R.styleable.ButtonsKtView_bkt_radiusBottomLeft, ResolutionUtil.dpToPx(context, config.radiusBottomLeft).toFloat())
            radiusBottomRight = typedArray.getDimension(R.styleable.ButtonsKtView_bkt_radiusBottomRight, ResolutionUtil.dpToPx(context, config.radiusBottomRight).toFloat())
        }

        renderBackground()
    }

    private fun renderBuilderConfigurations() {
        // Override layoutParams
        val layoutParams = RelativeLayout.LayoutParams(config.width, config.height)

        // Margin
        if (config.margin > 0) {
            layoutParams.setMargins(config.margin, config.margin, config.margin, config.margin)
        } else {
            layoutParams.setMargins(config.marginLeft, config.marginTop, config.marginRight, config.marginBottom)
        }

        // Padding
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

        renderBorder(defaultDrawable)
        renderBorder(disabledDrawable)


        defaultDrawable.setColor(config.defaultBackgroundColor)
        focusedDrawable.setColor(config.focusedBackgroundColor)
        disabledDrawable.setColor(config.disabledBackgroundColor)

        renderBackground(defaultDrawable, focusedDrawable, disabledDrawable)
        renderSubViews()
    }

    private fun renderSubViews() {
        if (config.iconResource != -1) {
            renderFontAwesomeResourceView()
        } else {
            renderIconView()
        }
        renderTextView()
    }

    private fun renderFontAwesomeResourceView() {
        val layoutParams = LinearLayout.LayoutParams(ResolutionUtil.dpToPx(context, config.iconArea), ResolutionUtil.dpToPx(context, config.iconArea))
        layoutParams.gravity = Gravity.CENTER_VERTICAL

        // Icon Margin
        if (config.iconMargin > 0) {
            layoutParams.setMargins(config.iconMargin, config.iconMargin, config.iconMargin, config.iconMargin)
        } else {
            layoutParams.setMargins(config.iconMarginLeft, config.iconMarginTop, config.iconMarginRight, config.iconMarginBottom)
        }

        val iconView = TextView(context)
        iconView.layoutParams = layoutParams

        // Icon Padding
        if (config.iconPadding > 0) {
            iconView.setPadding(config.iconPadding, config.iconPadding, config.iconPadding, config.iconPadding)
        } else {
            iconView.setPadding(config.iconPaddingLeft, config.iconPaddingTop, config.iconPaddingRight, config.iconPaddingBottom)
        }

        iconView.setTextSize(TypedValue.COMPLEX_UNIT_SP, config.textSize)
        iconView.setTextColor(config.textColor)

        // Render TypeFace
        // Font Awesome
        if(ButtonKtSingleton.typeface == null){
            ButtonKtSingleton.typeface = Typeface.createFromAsset(context.assets, "fontawesome_solid.ttf")
        }

        iconView.typeface = ButtonKtSingleton.typeface
        iconView.gravity = Gravity.CENTER

        // Load icon
        val iconsArray = resources.getStringArray(R.array.FontAwesome)
        iconView.text = iconsArray[config.iconResource]

        addView(iconView)
    }

    private fun renderIconView() {
        if (config.iconDrawable != -1 && config.iconResource == -1) {
            val layoutParams = LinearLayout.LayoutParams(ResolutionUtil.dpToPx(context, config.iconArea), ResolutionUtil.dpToPx(context, config.iconArea))
            layoutParams.gravity = Gravity.CENTER_VERTICAL

            // Icon Margin
            if (config.iconMargin > 0) {
                layoutParams.setMargins(config.iconMargin, config.iconMargin, config.iconMargin, config.iconMargin)
            } else {
                layoutParams.setMargins(config.iconMarginLeft, config.iconMarginTop, config.iconMarginRight, config.iconMarginBottom)
            }


            val imageView = ImageView(context)
            imageView.layoutParams = layoutParams
            imageView.setImageResource(config.iconDrawable)
            imageView.setColorFilter(config.iconTint)

            // Icon Padding
            if (config.iconPadding > 0) {
                imageView.setPadding(config.iconPadding, config.iconPadding, config.iconPadding, config.iconPadding)
            } else {
                imageView.setPadding(config.iconPaddingLeft, config.iconPaddingTop, config.iconPaddingRight, config.iconPaddingBottom)
            }


            // If disabled
            if (!config.isEnabled) {
                imageView.setColorFilter(config.disabledIconTint)
            }

            addView(imageView)
        }
    }

    private fun renderTextView() {
        if (config.isTextAllCaps) {
            config.text = config.text.toUpperCase()
        }

        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        layoutParams.gravity = config.textGravity

        if (config.iconDrawable != -1) {
            layoutParams.setMargins(15, 0, 0, 0)
        }

        val textView = TextView(context)
        textView.layoutParams = layoutParams
        textView.text = config.text
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, config.textSize)
        textView.setTextColor(config.textColor)
        textView.setTypeface(textView.typeface, config.textStyle)

        if (!config.isEnabled) {
            textView.setTextColor(config.disabledTextColor)
        }

        addView(textView)
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

    private fun renderBorder(gradientDrawable: GradientDrawable) {
        if (config.borderWidth > 0) {
            val states = arrayOf(
                    intArrayOf(android.R.attr.state_pressed),
                    intArrayOf(android.R.attr.state_focused),
                    intArrayOf(android.R.attr.enabled),
                    intArrayOf(-android.R.attr.state_pressed))

            val color = intArrayOf(config.focusedBorderColor, config.focusedBorderColor, config.defaultBorderColor, config.disabledBorderColor)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                gradientDrawable.setStroke(config.borderWidth, ColorStateList(states, color))
            } else {
                gradientDrawable.setStroke(config.borderWidth, config.defaultBorderColor)
                if (!config.isEnabled) {
                    gradientDrawable.setStroke(config.borderWidth, Color.TRANSPARENT)
                }
            }
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
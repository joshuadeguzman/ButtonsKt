package io.jmdg.buttonskt.internal.helpers

import android.content.Context
import android.util.TypedValue


/**
 * Created by Joshua de Guzman on 22/07/2018.
 */

internal object ResolutionUtil {
    fun dpToPx(context: Context, dipValue: Float): Int {
        val metrics = context.resources.displayMetrics
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics).toInt()
    }
}
package com.invadercorps.poketest.module.common.view

import android.content.Context
import android.graphics.Color
import androidx.swiperefreshlayout.widget.CircularProgressDrawable

class DrawableFactory {
    companion object {
        @JvmStatic
        fun makeCircularProgressDrawable(context: Context, strokeWidth: Float = 5f, centerRadius: Float = 30f): CircularProgressDrawable {
            val circularProgressDrawable = CircularProgressDrawable(context)
            circularProgressDrawable.strokeWidth = strokeWidth
            circularProgressDrawable.centerRadius = centerRadius
            circularProgressDrawable.setColorSchemeColors(Color.MAGENTA, Color.RED, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE)
            circularProgressDrawable.start()
            return circularProgressDrawable
        }
    }
}
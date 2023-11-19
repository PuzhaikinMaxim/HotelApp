package com.mxpj.hotelapp.presentation

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.text.TextPaint
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputEditText

class SuffixEditText(
    context: Context,
    attributeSet: AttributeSet
): TextInputEditText(context, attributeSet) {

    var suffix = ""
    val textPaint = TextPaint()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val suffixXPosition = textPaint.measureText(text.toString()) + paddingLeft + letterSpacing
        canvas?.drawText(suffix, suffixXPosition, baseline.toFloat(), textPaint)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        textPaint.color = currentTextColor
        textPaint.textSize = textSize
        textPaint.textAlign = Paint.Align.LEFT
        textPaint.typeface = typeface
        textPaint.letterSpacing = letterSpacing
    }
}
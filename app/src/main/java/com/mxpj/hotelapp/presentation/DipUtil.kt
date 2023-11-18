package com.mxpj.hotelapp.presentation

import android.content.Context
import android.util.TypedValue

fun Float.getInDip(context: Context): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, context.resources.displayMetrics).toInt()
}
package com.mxpj.hotelapp.data

fun parseMinimalPrice(minimalPrice: Int): String {
    return minimalPrice.toString().chunked(3).joinToString(" ")
}
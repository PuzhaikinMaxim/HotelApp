package com.mxpj.data.mappers

fun parsePrice(minimalPrice: Int): String {
    return minimalPrice.toString().reversed().chunked(3).joinToString(" ").reversed()
}
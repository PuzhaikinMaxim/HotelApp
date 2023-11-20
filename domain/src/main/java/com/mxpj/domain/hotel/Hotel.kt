package com.mxpj.domain.hotel

data class Hotel(
    val id: Int,
    val name: String,
    val address: String,
    val minimal_price: String,
    val price_for_it: String,
    val rating: Int,
    val ratingName: String,
    val imageUrls: List<String>,
    val description: String,
    val peculiarities: List<String>
)
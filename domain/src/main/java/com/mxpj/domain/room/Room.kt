package com.mxpj.domain.room

data class Room(
    val id: Int,
    val name: String,
    val price: String,
    val pricePer: String,
    val peculiarities: List<String>,
    val imageUrls: List<String>
)
package com.mxpj.data.network.models

import com.google.gson.annotations.SerializedName

data class RoomDto(
    val id: Int,
    val name: String,
    val price: Int,
    @SerializedName("price_per")
    val pricePer: String,
    val peculiarities: List<String>,
    @SerializedName("image_urls")
    val imageUrls: List<String>
)
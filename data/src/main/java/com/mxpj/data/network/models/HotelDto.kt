package com.mxpj.data.network.models

import com.google.gson.annotations.SerializedName
import com.mxpj.data.network.models.AboutTheHotelDto

data class HotelDto(
    val id: Int,
    val name: String,
    @SerializedName("adress")
    val address: String,
    @SerializedName("minimal_price")
    val minimalPrice: Int,
    @SerializedName("price_for_it")
    val priceForIt: String,
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("rating_name")
    val ratingName: String,
    @SerializedName("image_urls")
    val imageUrls: List<String>,
    @SerializedName("about_the_hotel")
    val aboutTheHotel: AboutTheHotelDto
)
package com.mxpj.hotelapp.domain

data class Tourist(
    val id: Int,
    val touristNumber: String,
    val isHidden: Boolean,
    var touristData: TouristData,
    val touristDataValidity: TouristDataValidity = TouristDataValidity()
)
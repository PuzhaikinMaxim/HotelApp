package com.mxpj.hotelapp.data.network.models

import com.google.gson.annotations.SerializedName

data class BookingDto(
    val id: Int,
    @SerializedName("hotel_name")
    val hotelName: String,
    @SerializedName("hotel_adress")
    val hotelAddress: String,
    @SerializedName("horating")
    val rating: Int,
    @SerializedName("rating_name")
    val ratingName: String,
    val departure: String,
    @SerializedName("arrival_country")
    val arrivalCountry: String,
    @SerializedName("tour_date_start")
    val tourDateStart: String,
    @SerializedName("tour_date_stop")
    val tourDataEnd: String,
    @SerializedName("number_of_nights")
    val numberOfNights: Int,
    val room: String,
    val nutrition: String,
    @SerializedName("tour_price")
    val tourPrice: Int,
    @SerializedName("fuel_charge")
    val fuelCharge: Int,
    @SerializedName("service_charge")
    val serviceCharge: Int,
)
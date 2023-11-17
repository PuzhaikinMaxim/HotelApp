package com.mxpj.hotelapp.domain

interface HotelRepository {

    suspend fun getHotelData(): Hotel?
}
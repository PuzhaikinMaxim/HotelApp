package com.mxpj.domain.hotel

interface HotelRepository {

    suspend fun getHotelData(): Hotel?
}
package com.mxpj.hotelapp.data.network

import com.mxpj.hotelapp.data.network.models.HotelDto

interface HotelRemoteDataSource {

    suspend fun getHotelData(): HotelDto?
}
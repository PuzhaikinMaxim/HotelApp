package com.mxpj.data.network.hotel

import com.mxpj.data.network.models.HotelDto

interface HotelRemoteDataSource {

    suspend fun getHotelData(): HotelDto?
}
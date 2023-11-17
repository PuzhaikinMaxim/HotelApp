package com.mxpj.hotelapp.data.network

import com.mxpj.hotelapp.data.network.models.HotelDto
import javax.inject.Inject

class HotelRemoteDataSourceImpl @Inject constructor(
    private val service: HotelApiService
): HotelRemoteDataSource {

    override suspend fun getHotelData(): HotelDto? {
        val response = service.getHotelData()
        if(response.isSuccessful){
            return response.body()
        }
        return null
    }
}
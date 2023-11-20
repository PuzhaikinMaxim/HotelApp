package com.mxpj.data.network.hotel

import com.mxpj.data.network.models.HotelDto
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
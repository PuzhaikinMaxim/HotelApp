package com.mxpj.hotelapp.data

import com.mxpj.hotelapp.data.network.HotelRemoteDataSource
import com.mxpj.hotelapp.domain.Hotel
import com.mxpj.hotelapp.domain.HotelRepository
import javax.inject.Inject

class HotelRepositoryImpl @Inject constructor(
    private val hotelRemoteDataSource: HotelRemoteDataSource,
    private val hotelMapper: HotelMapper
): HotelRepository {

    override suspend fun getHotelData(): Hotel? {
        val hotelDto = hotelRemoteDataSource.getHotelData() ?: return null
        return hotelMapper.mapHotelDtoToHotel(hotelDto)
    }
}
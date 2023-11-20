package com.mxpj.data

import com.mxpj.data.mappers.HotelMapper
import com.mxpj.domain.hotel.Hotel
import com.mxpj.domain.hotel.HotelRepository
import com.mxpj.data.network.hotel.HotelRemoteDataSource
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
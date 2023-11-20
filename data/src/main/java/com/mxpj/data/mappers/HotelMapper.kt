package com.mxpj.data.mappers

import com.mxpj.domain.hotel.Hotel
import com.mxpj.data.network.models.HotelDto
import javax.inject.Inject

class HotelMapper @Inject constructor(){

    fun mapHotelDtoToHotel(hotelDto: HotelDto): Hotel {
        return Hotel(
            hotelDto.id,
            hotelDto.name,
            hotelDto.address,
            parsePrice(hotelDto.minimalPrice),
            hotelDto.priceForIt,
            hotelDto.rating,
            hotelDto.ratingName,
            hotelDto.imageUrls,
            hotelDto.aboutTheHotel.description,
            hotelDto.aboutTheHotel.peculiarities
        )
    }
}
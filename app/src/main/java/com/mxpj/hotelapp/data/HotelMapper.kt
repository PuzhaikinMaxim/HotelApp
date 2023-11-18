package com.mxpj.hotelapp.data

import com.mxpj.hotelapp.data.network.models.HotelDto
import com.mxpj.hotelapp.domain.Hotel
import javax.inject.Inject

class HotelMapper @Inject constructor(){

    fun mapHotelDtoToHotel(hotelDto: HotelDto): Hotel {
        return Hotel(
            hotelDto.id,
            hotelDto.name,
            hotelDto.address,
            parseMinimalPrice(hotelDto.minimalPrice),
            hotelDto.priceForIt,
            hotelDto.rating,
            hotelDto.ratingName,
            hotelDto.imageUrls,
            hotelDto.aboutTheHotel.description,
            hotelDto.aboutTheHotel.peculiarities
        )
    }
}
package com.mxpj.hotelapp.data

import com.mxpj.hotelapp.data.network.models.BookingDto
import com.mxpj.hotelapp.domain.Booking
import javax.inject.Inject

class BookingMapper @Inject constructor() {

    fun mapBookingDtoToBooking(bookingDto: BookingDto): Booking {
        val total = bookingDto.tourPrice + bookingDto.fuelCharge + bookingDto.serviceCharge
        return Booking(
            bookingDto.id,
            bookingDto.hotelName,
            bookingDto.hotelAddress,
            bookingDto.rating,
            bookingDto.ratingName,
            bookingDto.departure,
            bookingDto.arrivalCountry,
            bookingDto.tourDateStart,
            bookingDto.tourDataEnd,
            bookingDto.numberOfNights,
            bookingDto.room,
            bookingDto.nutrition,
            parsePrice(bookingDto.tourPrice),
            parsePrice(bookingDto.fuelCharge),
            parsePrice(bookingDto.serviceCharge),
            parsePrice(total)
        )
    }
}
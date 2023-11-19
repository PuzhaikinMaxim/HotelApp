package com.mxpj.hotelapp.domain

interface BookingRepository {
    suspend fun getBooking(): Booking?
}
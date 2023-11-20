package com.mxpj.domain.booking

interface BookingRepository {
    suspend fun getBooking(): Booking?
}
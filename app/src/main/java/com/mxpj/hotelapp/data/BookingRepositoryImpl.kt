package com.mxpj.hotelapp.data

import com.mxpj.hotelapp.data.network.BookingRemoteDataSource
import com.mxpj.hotelapp.domain.Booking
import com.mxpj.hotelapp.domain.BookingRepository
import javax.inject.Inject

class BookingRepositoryImpl @Inject constructor(
    private val bookingRemoteDataSource: BookingRemoteDataSource,
    private val bookingMapper: BookingMapper
): BookingRepository {

    override suspend fun getBooking(): Booking? {
        val booking = bookingRemoteDataSource.getBooking() ?: return null
        return bookingMapper.mapBookingDtoToBooking(booking)
    }
}
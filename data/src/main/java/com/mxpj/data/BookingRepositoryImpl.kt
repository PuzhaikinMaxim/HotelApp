package com.mxpj.data

import com.mxpj.data.mappers.BookingMapper
import com.mxpj.domain.booking.Booking
import com.mxpj.domain.booking.BookingRepository
import com.mxpj.data.network.booking.BookingRemoteDataSource
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
package com.mxpj.data.network.booking

import com.mxpj.data.network.models.BookingDto

interface BookingRemoteDataSource {

    suspend fun getBooking(): BookingDto?
}
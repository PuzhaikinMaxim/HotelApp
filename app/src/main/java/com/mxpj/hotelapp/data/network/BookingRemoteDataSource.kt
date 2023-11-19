package com.mxpj.hotelapp.data.network

import com.mxpj.hotelapp.data.network.models.BookingDto

interface BookingRemoteDataSource {

    suspend fun getBooking(): BookingDto?
}
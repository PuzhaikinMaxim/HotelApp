package com.mxpj.hotelapp.data.network

import com.mxpj.hotelapp.data.network.models.BookingDto
import retrofit2.Response
import retrofit2.http.GET

interface BookingApiService {

    @GET("63866c74-d593-432c-af8e-f279d1a8d2ff")
    suspend fun getBooking(): Response<BookingDto>
}
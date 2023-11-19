package com.mxpj.hotelapp.data.network

import com.mxpj.hotelapp.data.network.models.BookingDto
import javax.inject.Inject

class BookingRemoteDatasourceImpl @Inject constructor(
    private val service: BookingApiService
): BookingRemoteDataSource {

    override suspend fun getBooking(): BookingDto? {
        val response = service.getBooking()
        if(response.isSuccessful){
            return response.body()
        }
        return null
    }
}
package com.mxpj.hotelapp.data.network

import com.mxpj.hotelapp.data.network.models.HotelDto
import retrofit2.Response
import retrofit2.http.GET

interface HotelApiService {

    @GET("d144777c-a67f-4e35-867a-cacc3b827473")
    suspend fun getHotelData(): Response<HotelDto>
}
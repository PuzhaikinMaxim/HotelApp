package com.mxpj.hotelapp.data.network

import com.mxpj.hotelapp.data.network.models.RoomListDto
import retrofit2.Response
import retrofit2.http.GET

interface RoomApiService {

    @GET("8b532701-709e-4194-a41c-1a903af00195")
    suspend fun getRoomList(): Response<RoomListDto>
}
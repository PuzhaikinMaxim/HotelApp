package com.mxpj.hotelapp.data.network

import com.mxpj.hotelapp.data.network.models.RoomDto
import com.mxpj.hotelapp.data.network.models.RoomListDto

interface RoomRemoteDataSource {

    suspend fun getRoomList(): List<RoomDto>
}
package com.mxpj.data.network.room

import com.mxpj.data.network.models.RoomDto

interface RoomRemoteDataSource {

    suspend fun getRoomList(): List<RoomDto>
}
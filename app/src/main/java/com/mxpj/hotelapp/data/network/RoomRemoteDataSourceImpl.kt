package com.mxpj.hotelapp.data.network

import com.mxpj.hotelapp.data.network.models.RoomDto
import javax.inject.Inject

class RoomRemoteDataSourceImpl @Inject constructor(
    private val service: RoomApiService
): RoomRemoteDataSource {

    override suspend fun getRoomList(): List<RoomDto> {
        val response = service.getRoomList()
        if(response.isSuccessful){
            return response.body()?.rooms ?: listOf()
        }
        return listOf()
    }
}
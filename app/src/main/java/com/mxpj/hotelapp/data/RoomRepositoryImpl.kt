package com.mxpj.hotelapp.data

import com.mxpj.hotelapp.data.network.RoomRemoteDataSource
import com.mxpj.hotelapp.domain.Room
import com.mxpj.hotelapp.domain.RoomRepository
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(
    private val roomRemoteDataSource: RoomRemoteDataSource,
    private val roomMapper: RoomMapper
): RoomRepository {

    override suspend fun getRoomList(): List<Room> {
        val roomDtoList = roomRemoteDataSource.getRoomList()
        return roomMapper.mapRoomDtoListToRoomList(roomDtoList)
    }
}
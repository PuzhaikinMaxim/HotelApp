package com.mxpj.data

import com.mxpj.data.mappers.RoomMapper
import com.mxpj.domain.room.Room
import com.mxpj.domain.room.RoomRepository
import com.mxpj.data.network.room.RoomRemoteDataSource
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
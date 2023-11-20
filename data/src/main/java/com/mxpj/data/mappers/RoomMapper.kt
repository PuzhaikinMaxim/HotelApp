package com.mxpj.data.mappers

import com.mxpj.domain.room.Room
import com.mxpj.data.network.models.RoomDto
import javax.inject.Inject

class RoomMapper @Inject constructor() {

    fun mapRoomDtoListToRoomList(list: List<RoomDto>): List<Room> {
        return list.map { mapRoomDtoToRoom(it) }
    }

    fun mapRoomDtoToRoom(roomDto: RoomDto): Room {
        return Room(
            roomDto.id,
            roomDto.name,
            parsePrice(roomDto.price),
            roomDto.pricePer,
            roomDto.peculiarities,
            roomDto.imageUrls
        )
    }
}
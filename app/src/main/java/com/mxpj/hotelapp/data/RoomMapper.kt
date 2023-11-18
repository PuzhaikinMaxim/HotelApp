package com.mxpj.hotelapp.data

import com.mxpj.hotelapp.data.network.models.RoomDto
import com.mxpj.hotelapp.domain.Room
import javax.inject.Inject

class RoomMapper @Inject constructor() {

    fun mapRoomDtoListToRoomList(list: List<RoomDto>): List<Room> {
        return list.map { mapRoomDtoToRoom(it) }
    }

    fun mapRoomDtoToRoom(roomDto: RoomDto): Room {
        return Room(
            roomDto.id,
            roomDto.name,
            parseMinimalPrice(roomDto.price),
            roomDto.pricePer,
            roomDto.peculiarities,
            roomDto.imageUrls
        )
    }
}
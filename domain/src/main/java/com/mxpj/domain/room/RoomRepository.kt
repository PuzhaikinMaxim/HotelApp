package com.mxpj.domain.room

interface RoomRepository {

    suspend fun getRoomList(): List<Room>
}
package com.mxpj.hotelapp.domain

interface RoomRepository {

    suspend fun getRoomList(): List<Room>
}
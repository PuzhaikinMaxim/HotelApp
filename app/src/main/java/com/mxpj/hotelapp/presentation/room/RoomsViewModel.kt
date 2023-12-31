package com.mxpj.hotelapp.presentation.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mxpj.domain.room.Room
import com.mxpj.domain.room.RoomRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class RoomsViewModel @Inject constructor(
    private val roomRepository: RoomRepository
): ViewModel() {

    private val _roomList = MutableLiveData<List<Room>>()
    val roomList: LiveData<List<Room>>
        get() = _roomList

    init {
        viewModelScope.launch {
             val roomList = roomRepository.getRoomList()
            _roomList.postValue(roomList)
        }
    }
}
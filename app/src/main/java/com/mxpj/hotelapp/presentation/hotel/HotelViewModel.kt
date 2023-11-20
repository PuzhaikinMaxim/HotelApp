package com.mxpj.hotelapp.presentation.hotel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mxpj.domain.hotel.Hotel
import com.mxpj.domain.hotel.HotelRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class HotelViewModel @Inject constructor(
    private val hotelRepository: HotelRepository
): ViewModel() {

    private val _hotelData = MutableLiveData<Hotel>()
    val hotelData: LiveData<Hotel>
        get() = _hotelData

    init {
        viewModelScope.launch {
            val hotel = hotelRepository.getHotelData() ?: return@launch
            _hotelData.postValue(hotel)
        }
    }
}
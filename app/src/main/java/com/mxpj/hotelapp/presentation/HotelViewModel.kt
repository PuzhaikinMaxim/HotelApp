package com.mxpj.hotelapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mxpj.hotelapp.domain.Hotel
import com.mxpj.hotelapp.domain.HotelRepository
import kotlinx.coroutines.Dispatchers
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
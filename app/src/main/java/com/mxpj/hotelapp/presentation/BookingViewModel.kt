package com.mxpj.hotelapp.presentation

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mxpj.hotelapp.domain.Booking
import com.mxpj.hotelapp.domain.BookingRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class BookingViewModel @Inject constructor(
    private val bookingRepository: BookingRepository
): ViewModel() {

    private val _booking = MutableLiveData<Booking>()
    val booking: LiveData<Booking>
        get() = _booking

    private val _emailError = MutableLiveData<Boolean>()
    val emailError: LiveData<Boolean>
        get() = _emailError

    init {
        viewModelScope.launch {
            val newBooking = bookingRepository.getBooking()
            newBooking?.let {
                _booking.postValue(it)
            }
        }
    }

    fun resetEmailError() {
        _emailError.value = false
    }

    fun checkForEmailError(email: String) {
        val isNotMatches = !Patterns.EMAIL_ADDRESS.matcher(email).matches()
        _emailError.value = isNotMatches
    }
}
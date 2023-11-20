package com.mxpj.hotelapp.di

import androidx.lifecycle.ViewModel
import com.mxpj.hotelapp.presentation.booking.BookingViewModel
import com.mxpj.hotelapp.presentation.hotel.HotelViewModel
import com.mxpj.hotelapp.presentation.room.RoomsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HotelViewModel::class)
    fun hotelViewModel(viewModel: HotelViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RoomsViewModel::class)
    fun roomsViewModel(viewModel: RoomsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BookingViewModel::class)
    fun bookingViewModel(viewModel: BookingViewModel): ViewModel
}
package com.mxpj.hotelapp.di

import androidx.lifecycle.ViewModel
import com.mxpj.hotelapp.presentation.HotelViewModel
import com.mxpj.hotelapp.presentation.RoomsViewModel
import com.puj.testtaskonlineshop.di.ViewModelKey
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
}
package com.puj.testtaskonlineshop.di

import androidx.lifecycle.ViewModel
import com.mxpj.hotelapp.presentation.HotelViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HotelViewModel::class)
    fun hotelViewModel(viewModel: HotelViewModel): ViewModel
}
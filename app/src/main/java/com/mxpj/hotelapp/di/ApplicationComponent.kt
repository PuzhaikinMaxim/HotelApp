package com.mxpj.hotelapp.di

import android.app.Application
import com.mxpj.hotelapp.presentation.booking.BookingFragment
import com.mxpj.hotelapp.presentation.hotel.HotelFragment
import com.mxpj.hotelapp.presentation.room.RoomsFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [
    DataModule::class,
    ViewModelModule::class
])
@ApplicationScope
interface ApplicationComponent {

    fun inject(hotelFragment: HotelFragment)

    fun inject(roomsFragment: RoomsFragment)

    fun inject(bookingFragment: BookingFragment)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}
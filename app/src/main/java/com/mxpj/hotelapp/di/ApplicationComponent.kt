package com.mxpj.hotelapp.di

import android.app.Application
import com.mxpj.hotelapp.presentation.HotelFragment
import com.puj.testtaskonlineshop.di.ApplicationScope
import com.puj.testtaskonlineshop.di.ViewModelModule
import dagger.BindsInstance
import dagger.Component

@Component(modules = [
    DataModule::class,
    ViewModelModule::class
])
@ApplicationScope
interface ApplicationComponent {

    fun inject(hotelFragment: HotelFragment)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}
package com.mxpj.hotelapp.presentation

import android.app.Application
import com.mxpj.hotelapp.di.DaggerApplicationComponent

class HotelApplication: Application() {

    val component by lazy { DaggerApplicationComponent.factory().create(this) }
}
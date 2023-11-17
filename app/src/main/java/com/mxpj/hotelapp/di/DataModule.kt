package com.mxpj.hotelapp.di

import com.mxpj.hotelapp.data.HotelRepositoryImpl
import com.mxpj.hotelapp.data.network.HotelApiService
import com.mxpj.hotelapp.data.network.HotelRemoteDataSource
import com.mxpj.hotelapp.data.network.HotelRemoteDataSourceImpl
import com.mxpj.hotelapp.data.network.ServiceFactory
import com.mxpj.hotelapp.domain.HotelRepository
import com.puj.testtaskonlineshop.di.ApplicationScope
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindHotelRemoteDataSource(impl: HotelRemoteDataSourceImpl): HotelRemoteDataSource

    @Binds
    @ApplicationScope
    fun bindHotelRepository(impl: HotelRepositoryImpl): HotelRepository

    companion object {

        @Provides
        @ApplicationScope
        fun provideHotelApiService(): HotelApiService {
            return ServiceFactory.create(HotelApiService::class.java)
        }
    }
}
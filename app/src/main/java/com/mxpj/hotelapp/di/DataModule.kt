package com.mxpj.hotelapp.di

import com.mxpj.data.BookingRepositoryImpl
import com.mxpj.data.HotelRepositoryImpl
import com.mxpj.data.RoomRepositoryImpl
import com.mxpj.data.network.*
import com.mxpj.data.network.booking.BookingApiService
import com.mxpj.data.network.booking.BookingRemoteDataSource
import com.mxpj.data.network.booking.BookingRemoteDatasourceImpl
import com.mxpj.data.network.hotel.HotelApiService
import com.mxpj.data.network.hotel.HotelRemoteDataSource
import com.mxpj.data.network.hotel.HotelRemoteDataSourceImpl
import com.mxpj.data.network.room.RoomApiService
import com.mxpj.data.network.room.RoomRemoteDataSource
import com.mxpj.data.network.room.RoomRemoteDataSourceImpl
import com.mxpj.domain.booking.BookingRepository
import com.mxpj.domain.hotel.HotelRepository
import com.mxpj.domain.room.RoomRepository
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

    @Binds
    @ApplicationScope
    fun bindRoomRemoteDataSource(impl: RoomRemoteDataSourceImpl): RoomRemoteDataSource

    @Binds
    @ApplicationScope
    fun bindRoomRepository(impl: RoomRepositoryImpl): RoomRepository

    @Binds
    @ApplicationScope
    fun bindBookingRepository(impl: BookingRepositoryImpl): BookingRepository

    @Binds
    @ApplicationScope
    fun bindBookingDataSource(impl: BookingRemoteDatasourceImpl): BookingRemoteDataSource

    companion object {

        @Provides
        @ApplicationScope
        fun provideHotelApiService(): HotelApiService {
            return ServiceFactory.create(HotelApiService::class.java)
        }

        @Provides
        @ApplicationScope
        fun provideRoomApiService(): RoomApiService {
            return ServiceFactory.create(RoomApiService::class.java)
        }

        @Provides
        @ApplicationScope
        fun provideBookingApiService(): BookingApiService {
            return ServiceFactory.create(BookingApiService::class.java)
        }
    }
}
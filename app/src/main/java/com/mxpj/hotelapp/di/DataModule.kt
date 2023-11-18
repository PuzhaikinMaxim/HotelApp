package com.mxpj.hotelapp.di

import com.mxpj.hotelapp.data.HotelRepositoryImpl
import com.mxpj.hotelapp.data.RoomRepositoryImpl
import com.mxpj.hotelapp.data.network.*
import com.mxpj.hotelapp.domain.HotelRepository
import com.mxpj.hotelapp.domain.RoomRepository
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
    }
}
package com.test.videosstories.common.di

import com.test.videosstories.common.repository.remote.INetworkService
import com.test.videosstories.common.repository.remote.NetworkService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class NetworkModule {

    @Binds
    abstract fun providesNetworkService(networkService: NetworkService): INetworkService
}
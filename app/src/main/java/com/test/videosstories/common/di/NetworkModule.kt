package com.test.videosstories.common.di

import com.test.videosstories.common.repository.remote.INetworkService
import com.test.videosstories.common.repository.remote.NetworkService
import dagger.Binds
import dagger.Module

@Module
abstract class NetworkModule {

    @Binds
    abstract fun providesNetworkService(networkService: NetworkService): INetworkService
}
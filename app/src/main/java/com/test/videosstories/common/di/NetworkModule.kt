package com.test.videosstories.common.di

import com.test.videosstories.common.repository.remote.INetworkService
import com.test.videosstories.common.repository.remote.NetworkService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesNetworkService(): INetworkService {
        return NetworkService()
    }
}
package com.test.videosstories.common.di

import com.test.videosstories.common.repository.remote.FakeNetworkService
import com.test.videosstories.common.repository.remote.INetworkService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TestNetworkModule {

    @Provides
    @Singleton
    fun providesNetworkService(): INetworkService {
        return FakeNetworkService()
    }
}
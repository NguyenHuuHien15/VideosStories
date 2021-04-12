package com.test.videosstories.common.di

import com.test.videosstories.common.repository.remote.FakeNetworkService
import com.test.videosstories.common.repository.remote.INetworkService
import dagger.Binds
import dagger.Module

@Module
abstract class TestNetworkModule {

    @Binds
    abstract fun providesNetworkService(networkService: FakeNetworkService): INetworkService
}
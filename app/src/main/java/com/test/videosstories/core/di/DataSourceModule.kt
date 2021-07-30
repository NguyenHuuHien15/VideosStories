package com.test.videosstories.core.di

import com.test.videosstories.data.local.ILocalDataSource
import com.test.videosstories.data.local.LocalDataSource
import com.test.videosstories.data.remote.IRemoteDataSource
import com.test.videosstories.data.remote.RemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class DataSourceModule() {

    @Singleton
    @Binds
    abstract fun provideRemoteDataSource(remoteDataSource: RemoteDataSource): IRemoteDataSource

    @Singleton
    @Binds
    abstract fun provideLocalDataSource(localDataSource: LocalDataSource): ILocalDataSource
}

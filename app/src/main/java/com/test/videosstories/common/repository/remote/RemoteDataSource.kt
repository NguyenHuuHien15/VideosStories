package com.test.videosstories.common.repository.remote

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val networkService: INetworkService) {

    suspend fun getRemoteItems(): NetworkItemsCollection {
        return networkService.getData()
    }

}
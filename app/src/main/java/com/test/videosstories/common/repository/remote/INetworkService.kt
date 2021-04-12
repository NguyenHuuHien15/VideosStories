package com.test.videosstories.common.repository.remote

interface INetworkService {
    suspend fun getData(): NetworkItemsCollection
}
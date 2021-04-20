package com.test.videosstories.common.repository.remote

interface INetworkService {

    fun getUrl(): String
    suspend fun getData(): NetworkItemsCollection

}
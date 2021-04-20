package com.test.videosstories.common.repository.remote

import com.test.videosstories.common.repository.remote.model.NetworkItemsCollection

interface INetworkService {

    fun getUrl(): String
    suspend fun getData(): NetworkItemsCollection

}
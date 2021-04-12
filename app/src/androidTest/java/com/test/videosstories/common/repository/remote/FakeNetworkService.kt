package com.test.videosstories.common.repository.remote

import javax.inject.Inject

class FakeNetworkService @Inject constructor() : INetworkService {
    override suspend fun getData(): NetworkItemsCollection {
        return NetworkItemsCollection()
    }
}
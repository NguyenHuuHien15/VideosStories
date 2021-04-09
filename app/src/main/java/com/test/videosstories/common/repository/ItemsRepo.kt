package com.test.videosstories.common.repository

import com.test.videosstories.common.repository.source.local.ItemDatabase
import com.test.videosstories.common.repository.source.remote.NetworkCaller
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ItemsRepo(private val database: ItemDatabase) {
    suspend fun refreshItems() {
        withContext(Dispatchers.IO) {
            val remoteItems = NetworkCaller.remoteItems.getData()
            database.itemDao.insertAll(remoteItems.toEntities())
        }
    }
}
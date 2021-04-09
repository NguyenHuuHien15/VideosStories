package com.test.videosstories.common.repository

import android.util.Log
import com.test.videosstories.common.repository.local.ItemDatabase
import com.test.videosstories.common.repository.remote.NetworkCaller
import com.test.videosstories.common.repository.remote.NetworkItemsCollection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ItemsRepo(private val database: ItemDatabase) {
    val LOG_TAG = ItemsRepo::class.simpleName
    suspend fun refreshItems() : NetworkItemsCollection {
        val data = NetworkCaller.getData()
        Log.d(LOG_TAG, "Data : $data" )
        Log.d(LOG_TAG, "Data : ${data.videos?.size}" )
        return data
    }
}
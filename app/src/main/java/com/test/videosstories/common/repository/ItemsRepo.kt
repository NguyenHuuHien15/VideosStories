package com.test.videosstories.common.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.test.videosstories.common.repository.local.ItemDatabase
import com.test.videosstories.common.repository.local.entity.toModels
import com.test.videosstories.common.repository.remote.NetworkCaller
import com.test.videosstories.common.repository.remote.NetworkItemsCollection
import com.test.videosstories.common.util.networkToEntities
import com.test.videosstories.list.model.ItemForView

class ItemsRepo(private val database: ItemDatabase) {
    val LOG_TAG = ItemsRepo::class.simpleName
    suspend fun refreshItems(): NetworkItemsCollection {
        val data = NetworkCaller.getData()
        Log.d(LOG_TAG, "Data : $data")
        Log.d(LOG_TAG, "Data : ${data.videos?.size}")
        return data
    }

    suspend fun getAndSaveNetworkItemsToDB() {
        val data = NetworkCaller.getData()
        val entities = networkToEntities(data)
        database.itemDao.insertAll(entities)
    }

    fun getAllItemsFromDB(): LiveData<List<ItemForView>> {
        return Transformations.map(database.itemDao.getItems()) {
            it.toModels()
        }
    }
}
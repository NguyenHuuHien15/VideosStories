package com.test.videosstories.common.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.test.videosstories.common.repository.local.ItemDatabase
import com.test.videosstories.common.repository.local.entity.toModels
import com.test.videosstories.common.repository.remote.NetworkCaller
import com.test.videosstories.common.util.networkToEntities
import com.test.videosstories.list.model.ItemForView

class ItemsRepo(private val database: ItemDatabase) {
    val LOG_TAG = ItemsRepo::class.simpleName

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

    suspend fun getItemFromDB(id: Int): ItemForView? {
        return database.itemDao.getItem(id)?.toModel()
    }
}
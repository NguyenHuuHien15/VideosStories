package com.test.videosstories.common.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.test.videosstories.common.repository.local.ItemDatabase
import com.test.videosstories.common.repository.remote.INetworkService
import com.test.videosstories.common.util.networkToEntities
import com.test.videosstories.common.util.toModels
import com.test.videosstories.list.model.ItemForView
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemsRepo @Inject constructor(private val networkService: INetworkService, private val database: ItemDatabase) {
    val LOG_TAG = ItemsRepo::class.simpleName

    @Throws(Exception::class)
    suspend fun getAndSaveNetworkItemsToDB() {
        val data = networkService.getData()
        val entities = networkToEntities(data)
        database.itemDao.insertAll(entities)
    }

    fun getAllItemsFromDB(): LiveData<List<ItemForView>> {
        return Transformations.map(database.itemDao.getItems()) {
            toModels(it)
        }
    }

    suspend fun getItemFromDB(id: Int): ItemForView? {
        return database.itemDao.getItem(id)?.toModel()
    }
}
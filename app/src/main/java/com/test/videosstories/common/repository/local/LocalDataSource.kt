package com.test.videosstories.common.repository.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.test.videosstories.common.repository.remote.model.NetworkItemsCollection
import com.test.videosstories.common.util.networkToEntities
import com.test.videosstories.common.util.toModels
import com.test.videosstories.common.model.ItemForView
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val itemDao: ItemDao) {

    fun getAllItemsFromDB(): LiveData<List<ItemForView>> {
        return Transformations.map(itemDao.getItems()) {
            toModels(it)
        }
    }

    suspend fun getItemFromDB(id: Int): ItemForView? {
        return itemDao.getItem(id)?.toModel()
    }

    suspend fun saveAllItemsToDB(items: NetworkItemsCollection) {
        val entities = networkToEntities(items)
        itemDao.insertAll(entities)
    }

}
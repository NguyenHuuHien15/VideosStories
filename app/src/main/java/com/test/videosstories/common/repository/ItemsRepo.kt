package com.test.videosstories.common.repository

import androidx.lifecycle.LiveData
import com.test.videosstories.common.repository.local.LocalDataSource
import com.test.videosstories.common.repository.remote.RemoteDataSource
import com.test.videosstories.list.model.ItemForView
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemsRepo @Inject constructor(private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource) {
    val LOG_TAG = ItemsRepo::class.simpleName

    @Throws(Exception::class)
    suspend fun getAndSaveNetworkItemsToDB() {
        val data = remoteDataSource.getRemoteItems()
        localDataSource.saveAllItemsToDB(data)
    }

    fun getAllItemsFromDB(): LiveData<List<ItemForView>> {
        return localDataSource.getAllItemsFromDB()
    }

    suspend fun getItemFromDB(id: Int): ItemForView? {
        return localDataSource.getItemFromDB(id)
    }
}
package com.test.videosstories.common.repository.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.videosstories.common.repository.local.entity.ItemEntity

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<ItemEntity>)

    @Query("SELECT * from item_entity_table WHERE id = :key")
    suspend fun getItem(key: Int): ItemEntity?

    @Query("SELECT * from item_entity_table")
    fun getItems(): LiveData<List<ItemEntity>>
}
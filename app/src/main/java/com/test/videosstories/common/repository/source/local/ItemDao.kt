package com.test.videosstories.common.repository.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.videosstories.common.repository.source.local.entity.ItemEntity

@Dao
interface ItemDao {
    @Query("select * from itementity")
    fun getItems(): LiveData<List<ItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<ItemEntity>)
}
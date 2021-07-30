package com.test.videosstories.data.local

import androidx.room.*
import com.test.videosstories.data.local.entity.SportEntity
import com.test.videosstories.data.local.entity.VideoAndSport

@Dao
interface SportDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<SportEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: SportEntity)

}
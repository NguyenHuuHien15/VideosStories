package com.test.videosstories.data.local

import androidx.room.*
import com.test.videosstories.data.local.entity.VideoAndSport
import com.test.videosstories.data.local.entity.VideoEntity

@Dao
interface VideoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<VideoEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: VideoEntity)

    @Transaction
    //@Query("SELECT * FROM VideoEntity")
    @Query("SELECT * FROM SportEntity")
    suspend fun getVideosAndSports(): List<VideoAndSport>

}
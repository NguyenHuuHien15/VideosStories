package com.test.videosstories.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.test.videosstories.data.local.entity.SportEntity
import com.test.videosstories.data.local.entity.VideoEntity

@Database(
    entities = [
        VideoEntity::class,
        SportEntity::class,
    ],
    version = 2,
    exportSchema = false
)

abstract class MyDatabase : RoomDatabase() {
    abstract val videoDao: VideoDao
    abstract val sportDao: SportDao
}

private lateinit var INSTANCE: MyDatabase

fun getDatabase(context: Context): MyDatabase {
    synchronized(MyDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext, MyDatabase::class.java, "medias").build()
        }
    }
    return INSTANCE
}

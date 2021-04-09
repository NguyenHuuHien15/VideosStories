package com.test.videosstories.common.repository.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.test.videosstories.common.repository.source.local.entity.ItemEntity

@Database(entities = [ItemEntity::class], version = 1)
abstract class ItemDatabase : RoomDatabase() {
    abstract val itemDao: ItemDao
}

private lateinit var INSTANCE: ItemDatabase

fun getDatabase(context: Context): ItemDatabase {
    synchronized(ItemDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext, ItemDatabase::class.java, "items").build()
        }
    }
    return INSTANCE
}

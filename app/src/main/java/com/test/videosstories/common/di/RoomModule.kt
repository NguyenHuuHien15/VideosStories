package com.test.videosstories.common.di

import android.content.Context
import com.test.videosstories.common.repository.local.ItemDao
import com.test.videosstories.common.repository.local.ItemDatabase
import com.test.videosstories.common.repository.local.getDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RoomModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): ItemDatabase {
        return getDatabase(context)
    }

    @Provides
    @Singleton
    fun providesDao(database: ItemDatabase): ItemDao {
        return database.itemDao
    }
}
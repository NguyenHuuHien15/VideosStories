package com.test.videosstories.common.di

import android.content.Context
import com.test.videosstories.common.repository.local.ItemDao
import com.test.videosstories.common.repository.local.ItemDatabase
import com.test.videosstories.data.local.MyDatabase
import com.test.videosstories.data.local.SportDao
import com.test.videosstories.data.local.VideoDao
import com.test.videosstories.data.local.getDatabase
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
    fun providesDatabase(@ApplicationContext context: Context): MyDatabase {
        return getDatabase(context)
    }

    @Provides
    @Singleton
    fun providesDao(database: MyDatabase): VideoDao {
        return database.videoDao
    }

    @Provides
    @Singleton
    fun providesSportDao(database: MyDatabase): SportDao {
        return database.sportDao
    }

    @Provides
    @Singleton
    fun providesItemDatabase(@ApplicationContext context: Context): ItemDatabase {
        return com.test.videosstories.common.repository.local.getDatabase(context)
    }

    @Provides
    @Singleton
    fun providesItemDao(database: ItemDatabase): ItemDao {
        return database.itemDao
    }
}
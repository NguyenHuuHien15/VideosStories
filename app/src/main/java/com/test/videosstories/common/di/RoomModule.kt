package com.test.videosstories.common.di

import android.content.Context
import com.test.videosstories.common.repository.local.ItemDatabase
import com.test.videosstories.common.repository.local.getDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    @Singleton
    fun providesDatabase(context: Context): ItemDatabase {
        return getDatabase(context)
    }
}
package com.test.videosstories.common.di

import android.content.Context
import com.test.videosstories.MainActivity
import com.test.videosstories.detail.view.StoryDetailsFragment
import com.test.videosstories.list.view.ItemsCollectionFragment
import com.test.videosstories.playvideo.view.PlayerVideoFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RoomModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(activity: MainActivity)
    fun inject(fragment: ItemsCollectionFragment)
    fun inject(fragment: StoryDetailsFragment)
    fun inject(fragment: PlayerVideoFragment)
}
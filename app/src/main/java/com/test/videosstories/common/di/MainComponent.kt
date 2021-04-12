package com.test.videosstories.common.di

import com.test.videosstories.MainActivity
import com.test.videosstories.detail.view.StoryDetailsFragment
import com.test.videosstories.list.view.ItemsCollectionFragment
import com.test.videosstories.playvideo.view.PlayerVideoFragment
import dagger.Subcomponent
import javax.inject.Singleton

@Subcomponent
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(activity: MainActivity)
    fun inject(fragment: ItemsCollectionFragment)
    fun inject(fragment: StoryDetailsFragment)
    fun inject(fragment: PlayerVideoFragment)
}

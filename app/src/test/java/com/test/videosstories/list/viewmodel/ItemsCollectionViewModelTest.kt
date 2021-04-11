package com.test.videosstories.list.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.test.videosstories.LiveDataTestUtil
import com.test.videosstories.common.repository.ItemsRepo
import com.test.videosstories.list.model.ItemForView
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class ItemsCollectionViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ItemsCollectionViewModel
    private lateinit var itemsRepo: ItemsRepo
    private lateinit var video: ItemForView
    private lateinit var story: ItemForView

    @Before
    fun setup() {
        itemsRepo = Mockito.mock(ItemsRepo::class.java)
        viewModel = ItemsCollectionViewModel(itemsRepo)

        video = ItemForView(
            1, "Video 1", "https://abc.com/vid.jpg", "https://abc.com/vid.mp4",
            11.1, 111, "Football", 1000, null, null, "John", true
        )

        story = ItemForView(
            2, "Story 2", null, null, 22.2, 222, "Volley",
            null, "Volley teaser content", "https://abc.com/volley.jpg", "David", false
        )
    }

    @Test
    fun getClickedItem() {
        assertEquals(LiveDataTestUtil.getValue(viewModel.clickedItem), null)
    }

    @Test
    fun getNeedNotifyNetworkError() {
        assertEquals(LiveDataTestUtil.getValue(viewModel.needNotifyNetworkError), false)
    }

    @Test
    fun onItemClicked() {
        viewModel.onItemClicked(video)
        assertEquals(LiveDataTestUtil.getValue(viewModel.clickedItem), video)

        viewModel.onItemClicked(story)
        assertEquals(LiveDataTestUtil.getValue(viewModel.clickedItem), story)
    }

    @Test
    fun doneNavigating() {
        viewModel.doneNavigating()
        assertEquals(LiveDataTestUtil.getValue(viewModel.clickedItem), null)
    }

    @Test
    fun doneNotifyNetworkError() {
        viewModel.doneNotifyNetworkError()
        assertEquals(LiveDataTestUtil.getValue(viewModel.needNotifyNetworkError), false)
    }
}
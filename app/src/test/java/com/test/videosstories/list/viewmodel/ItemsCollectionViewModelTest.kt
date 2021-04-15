package com.test.videosstories.list.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.test.videosstories.LiveDataTestUtil
import com.test.videosstories.common.repository.ItemsRepo
import com.test.videosstories.list.model.ItemForView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.exceptions.base.MockitoException
import org.mockito.Mockito.`when` as whenever

class ItemsCollectionViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ItemsCollectionViewModel
    private lateinit var itemsRepo: ItemsRepo
    private lateinit var video: ItemForView
    private lateinit var story: ItemForView

    @Before
    fun setup() {
        itemsRepo = mock(ItemsRepo::class.java)
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

    /*@ExperimentalCoroutinesApi
    @Test
    fun getAndSaveNetworkItemsToDB() = runBlockingTest {
        whenever(itemsRepo.getAndSaveNetworkItemsToDB()).thenReturn(Unit)

        viewModel.getAndSaveNetworkItemsToDB()

        assertEquals(false, LiveDataTestUtil.getValue(viewModel.needNotifyNetworkError))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getAndSaveNetworkItemsToDBWhenError() = runBlockingTest {
        whenever(itemsRepo.getAndSaveNetworkItemsToDB()).thenThrow(Exception())

        viewModel.getAndSaveNetworkItemsToDB()

        assertEquals(true, LiveDataTestUtil.getValue(viewModel.needNotifyNetworkError))
    }*/

    @Test
    fun getClickedItem() {
        assertEquals(null, LiveDataTestUtil.getValue(viewModel.clickedItem))
    }

    @Test
    fun getNeedNotifyNetworkError() {
        assertEquals(false, LiveDataTestUtil.getValue(viewModel.needNotifyNetworkError))
    }

    @Test
    fun onItemClicked() {
        viewModel.onItemClicked(video)
        assertEquals(video, LiveDataTestUtil.getValue(viewModel.clickedItem))

        viewModel.onItemClicked(story)
        assertEquals(story, LiveDataTestUtil.getValue(viewModel.clickedItem))
    }

    @Test
    fun doneNavigating() {
        viewModel.doneNavigating()
        assertEquals(null, LiveDataTestUtil.getValue(viewModel.clickedItem))
    }

    @Test
    fun doneNotifyNetworkError() {
        viewModel.doneNotifyNetworkError()
        assertEquals(false, LiveDataTestUtil.getValue(viewModel.needNotifyNetworkError))
    }
}
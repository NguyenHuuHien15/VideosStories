package com.test.videosstories.detail.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.test.videosstories.LiveDataTestUtil
import com.test.videosstories.common.repository.ItemsRepo
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class StoryDetailsViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: StoryDetailsViewModel
    private lateinit var itemsRepo: ItemsRepo

    @Before
    fun setUp() {
        itemsRepo = Mockito.mock(ItemsRepo::class.java)
        viewModel = StoryDetailsViewModel(itemsRepo)
    }

    @Test
    fun getItem() {
        assertEquals(null, LiveDataTestUtil.getValue(viewModel.item))
    }

    @Test
    fun getBackToHome() {
        assertEquals(false, LiveDataTestUtil.getValue(viewModel.backToHome))
    }

    @Test
    fun onBackClicked() {
        viewModel.onBackClicked()
        assertEquals(true, LiveDataTestUtil.getValue(viewModel.backToHome))
    }

    @Test
    fun doneBackToHome() {
        viewModel.doneBackToHome()
        assertEquals(false, LiveDataTestUtil.getValue(viewModel.backToHome))
    }
}
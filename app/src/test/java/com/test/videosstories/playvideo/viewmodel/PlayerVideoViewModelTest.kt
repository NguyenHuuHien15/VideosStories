package com.test.videosstories.playvideo.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.test.videosstories.LiveDataTestUtil
import com.test.videosstories.common.repository.ItemsRepo
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class PlayerVideoViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: PlayerVideoViewModel

    @Before
    fun setUp() {
        viewModel = PlayerVideoViewModel()
    }

    @Test
    fun getUrlVideo() {
        assertEquals(null, LiveDataTestUtil.getValue(viewModel.urlVideo))
    }

    @Test
    fun getBackToHome() {
        assertEquals(false, LiveDataTestUtil.getValue(viewModel.backToHome))
    }

    @Test
    fun setUrl() {
        viewModel.setUrl("https://abc.com/vid.mp4")
        assertEquals("https://abc.com/vid.mp4", LiveDataTestUtil.getValue(viewModel.urlVideo))
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
package com.test.videosstories.detail.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class StoryDetailsViewModelFactory(val app: Application, private val itemId: Int) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StoryDetailsViewModel::class.java)) {
            return StoryDetailsViewModel(app, itemId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}

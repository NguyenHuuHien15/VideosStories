package com.test.videosstories.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.videosstories.common.repository.ItemsRepo
import javax.inject.Inject

class StoryDetailsViewModelFactory @Inject constructor(private val itemsRepo: ItemsRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StoryDetailsViewModel::class.java)) {
            return StoryDetailsViewModel(itemsRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}

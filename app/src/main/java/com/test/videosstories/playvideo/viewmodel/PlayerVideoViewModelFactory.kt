package com.test.videosstories.playvideo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class PlayerVideoViewModelFactory @Inject constructor() : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlayerVideoViewModel::class.java)) {
            return PlayerVideoViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}

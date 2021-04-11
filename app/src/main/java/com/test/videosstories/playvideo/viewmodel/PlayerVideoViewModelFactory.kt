package com.test.videosstories.playvideo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PlayerVideoViewModelFactory(private val url: String) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlayerVideoViewModel::class.java)) {
            return PlayerVideoViewModel(url) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}

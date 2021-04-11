package com.test.videosstories.list.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ItemsCollectionViewModelFactory(val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemsCollectionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ItemsCollectionViewModel(app) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}
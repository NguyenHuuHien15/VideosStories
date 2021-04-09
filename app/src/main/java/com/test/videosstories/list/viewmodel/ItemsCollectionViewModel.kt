package com.test.videosstories.list.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.test.videosstories.common.repository.ItemsRepo
import com.test.videosstories.common.repository.source.local.getDatabase
import kotlinx.coroutines.launch
import java.io.IOException

class ItemsCollectionViewModel(application: Application) : AndroidViewModel(application) {
    private val itemsRepo = ItemsRepo(getDatabase(application))

    init {
        refreshItems()
    }

    private fun refreshItems() {
        viewModelScope.launch {
            try {
                itemsRepo.refreshItems()
            } catch (networkError: IOException) {
            }
        }
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ItemsCollectionViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ItemsCollectionViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}


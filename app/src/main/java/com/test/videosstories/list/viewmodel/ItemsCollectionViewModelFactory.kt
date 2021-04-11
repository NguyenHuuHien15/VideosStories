package com.test.videosstories.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.videosstories.common.repository.ItemsRepo
import javax.inject.Inject

class ItemsCollectionViewModelFactory @Inject constructor(private val itemsRepo: ItemsRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemsCollectionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ItemsCollectionViewModel(itemsRepo) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}
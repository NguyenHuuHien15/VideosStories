package com.test.videosstories.list.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.test.videosstories.common.repository.ItemsRepo
import com.test.videosstories.common.repository.local.getDatabase
import com.test.videosstories.list.model.ItemForView
import com.test.videosstories.list.view.ItemsCollectionFragment
import kotlinx.coroutines.launch
import java.io.IOException

class ItemsCollectionViewModel(application: Application) : AndroidViewModel(application) {
    //val LOG_TAG = ItemsCollectionViewModel::class.simpleName
    val LOG_TAG = ItemsCollectionFragment::class.simpleName

    private val itemsRepo = ItemsRepo(getDatabase(application))

    val itemsCollection: LiveData<List<ItemForView>> get() = itemsRepo.getAllItemsFromDB()

    private val _clickedItem: MutableLiveData<ItemForView> = MutableLiveData()
    val clickedItem: LiveData<ItemForView> get() = _clickedItem

    init {
        _clickedItem.value = null
        refreshItems()
    }

    private fun refreshItems() {
        viewModelScope.launch {
            try {
                itemsRepo.getAndSaveNetworkItemsToDB()
            } catch (networkError: IOException) {
            }
        }
    }

    fun onItemClicked(item: ItemForView) {
        _clickedItem.value = item
    }

    fun doneNavigatingToStoryDetails() {
        _clickedItem.value = null
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


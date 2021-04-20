package com.test.videosstories.list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.videosstories.common.repository.ItemsRepo
import com.test.videosstories.common.model.ItemForView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ItemsCollectionViewModel @Inject constructor(private val itemsRepo: ItemsRepo) : ViewModel() {
    val LOG_TAG = ItemsCollectionViewModel::class.simpleName

    val itemsCollection: LiveData<List<ItemForView>> get() = itemsRepo.getAllItemsFromDB()

    private val _clickedItem: MutableLiveData<ItemForView> = MutableLiveData()
    val clickedItem: LiveData<ItemForView> get() = _clickedItem

    private val _needNotifyNetworkError: MutableLiveData<Boolean> = MutableLiveData()
    val needNotifyNetworkError: LiveData<Boolean> get() = _needNotifyNetworkError

    init {
        _clickedItem.value = null
        _needNotifyNetworkError.value = false
        getAndSaveNetworkItemsToDB()
    }

    fun getAndSaveNetworkItemsToDB() {
        viewModelScope.launch {
            try {
                itemsRepo.getAndSaveNetworkItemsToDB()
            } catch (networkError: IOException) {
                _needNotifyNetworkError.value = true
            }
        }
    }

    fun onItemClicked(item: ItemForView) {
        _clickedItem.value = item
    }

    fun doneNavigating() {
        _clickedItem.value = null
    }

    fun doneNotifyNetworkError() {
        _needNotifyNetworkError.value = false
    }

}


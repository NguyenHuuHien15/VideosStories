package com.test.videosstories.list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.videosstories.list.model.ItemForView

class ItemViewModel : ViewModel() {
    val LOG_TAG = ItemViewModel::class.simpleName

    private val _item: MutableLiveData<ItemForView> = MutableLiveData()
    val item: LiveData<ItemForView> get() = _item

    fun setItem(itemForView: ItemForView) {
        _item.value = itemForView
    }
}
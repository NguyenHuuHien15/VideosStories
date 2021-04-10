package com.test.videosstories.list.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.test.videosstories.common.repository.ItemsRepo
import com.test.videosstories.common.repository.local.getDatabase
import com.test.videosstories.list.model.ItemForView
import com.test.videosstories.list.view.ItemsCollectionFragment
import kotlinx.coroutines.launch
import org.apache.commons.lang3.StringUtils
import java.io.IOException

class ItemsCollectionViewModel(application: Application) : AndroidViewModel(application) {
    //val LOG_TAG = ItemsCollectionViewModel::class.simpleName
    val LOG_TAG = ItemsCollectionFragment::class.simpleName

    private val itemsRepo = ItemsRepo(getDatabase(application))

    private val _itemsCollection: MutableLiveData<List<ItemForView>> = MutableLiveData()
    val itemsCollection: LiveData<List<ItemForView>> get() = _itemsCollection

    private val _clickedItem: MutableLiveData<ItemForView> = MutableLiveData()
    val clickedItem: LiveData<ItemForView> get() = _clickedItem

    init {
        _clickedItem.value = null
        refreshItems()
    }

    private fun refreshItems() {
        viewModelScope.launch {
            try {
                val networkItemsCollection = itemsRepo.refreshItems()
                val list = mutableListOf<ItemForView>()
                val videos = networkItemsCollection.videos
                if (videos != null) {
                    for (item in videos) {
                        list.add(
                            ItemForView(
                                item.id, item.title, item.thumb, item.url, item.date, item.sport.id, item.sport.name, item.views,
                                StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, true
                            )
                        )
                    }
                }

                val stories = networkItemsCollection.stories
                if (stories != null) {
                    for (item in stories) {
                        list.add(
                            ItemForView(
                                item.id, item.title, StringUtils.EMPTY, StringUtils.EMPTY, item.date, item.sport.id, item.sport.name,
                                null, item.teaser, item.image, item.author, false
                            )
                        )
                    }
                }

                _itemsCollection.value = list

                Log.d(LOG_TAG, "Data : ${itemsCollection.value?.size}")
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


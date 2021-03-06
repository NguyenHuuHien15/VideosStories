package com.test.videosstories.detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.videosstories.common.repository.ItemsRepo
import com.test.videosstories.common.model.ItemForView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoryDetailsViewModel @Inject constructor(private val itemsRepo: ItemsRepo) : ViewModel() {

    private val _item = MutableLiveData<ItemForView>()
    val item: LiveData<ItemForView> get() = _item

    private val _backToHome = MutableLiveData<Boolean>()
    val backToHome: LiveData<Boolean> get() = _backToHome

    init {
        _backToHome.value = false
    }

    fun setStoryId(storyId: Int) {
        viewModelScope.launch {
            _item.value = itemsRepo.getItemFromDB(storyId)
        }
    }

    fun onBackClicked() {
        _backToHome.value = true
    }

    fun doneBackToHome() {
        // Reinit
        _backToHome.value = false
    }

}

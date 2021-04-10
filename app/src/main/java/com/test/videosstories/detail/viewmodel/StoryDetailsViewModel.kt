package com.test.videosstories.detail.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.videosstories.common.repository.ItemsRepo
import com.test.videosstories.common.repository.local.getDatabase
import com.test.videosstories.list.model.ItemForView
import kotlinx.coroutines.launch

class StoryDetailsViewModel(application: Application, storyId: Int) : ViewModel() {

    private val itemsRepo = ItemsRepo(getDatabase(application))

    private val _item = MutableLiveData<ItemForView>()
    val item: LiveData<ItemForView> get() = _item

    private val _backToHome = MutableLiveData<Boolean>()
    val backToHome: LiveData<Boolean> get() = _backToHome

    init {
        _backToHome.value = false

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

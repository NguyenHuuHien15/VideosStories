package com.test.videosstories.presentation.videos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.videosstories.domain.Video
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VideoItemViewModel @Inject constructor() : ViewModel() {
    val LOG_TAG = VideoItemViewModel::class.simpleName

    private val _item: MutableLiveData<Video> = MutableLiveData()
    val item: LiveData<Video> get() = _item

    fun setItem(video: Video) {
        _item.value = video
    }

}


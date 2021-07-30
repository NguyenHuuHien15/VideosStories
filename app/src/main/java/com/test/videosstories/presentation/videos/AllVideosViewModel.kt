package com.test.videosstories.presentation.videos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.test.videosstories.data.DataResult
import com.test.videosstories.domain.Video
import com.test.videosstories.presentation.CommonViewModel
import com.test.videosstories.presentation.WorkState
import com.test.videosstories.usecase.CollectionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AllVideosViewModel @Inject constructor(private val useCase: CollectionUseCase) : CommonViewModel() {
    val LOG_TAG = AllVideosViewModel::class.simpleName

    private val _clickedItem: MutableLiveData<Video> = MutableLiveData()
    val clickedItem: LiveData<Video> get() = _clickedItem

    private val _allVideos: MutableLiveData<List<Video>> = MutableLiveData()
    val allVideos: LiveData<List<Video>> get() = _allVideos

    init {
        _clickedItem.value = null
        _allVideos.value = emptyList()
        viewModelScope.launch {
            _workState.postValue(WorkState.IN_PROGRESS)
            val videosInDB = withContext(Dispatchers.IO) {
                useCase.findVideos()
            }

            if (videosInDB is DataResult.Success && videosInDB.data.isNotEmpty()) {
                _allVideos.postValue(videosInDB.data)
                _workState.postValue(WorkState.DONE)
                Log.d(LOG_TAG, "Find in DB done")
            } else {
                val mediaCollection = withContext(Dispatchers.IO) {
                    useCase.getMediaCollection()
                }
                Log.d(LOG_TAG, "Not in DB, Get media from server done")

                withContext(Dispatchers.IO) {
                    if (mediaCollection is DataResult.Success) {
                        val videos = mediaCollection.data.videos
                        _allVideos.postValue(videos)
                        Log.d(LOG_TAG, "Display on view done")
                        useCase.saveVideos(videos)
                        Log.d(LOG_TAG, "Save videos done : ${videos.size}")
                    }
                }
            }
        }
    }

    fun onItemClicked(item: Video) {
        _clickedItem.value = item
    }

    fun doneNavigating() {
        _clickedItem.value = null
    }

}


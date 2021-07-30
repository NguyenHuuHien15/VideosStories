package com.test.videosstories.presentation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.test.videosstories.data.DataResult
import com.test.videosstories.domain.MediaCollection
import com.test.videosstories.domain.WebServiceParams
import com.test.videosstories.presentation.CommonViewModel
import com.test.videosstories.presentation.WorkState
import com.test.videosstories.usecase.CollectionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: CollectionUseCase,
    private val defaultWebServiceParams: WebServiceParams
) : CommonViewModel() {

    private val _mediaCollection: MutableLiveData<DataResult<MediaCollection>> = MutableLiveData()
    val mediaCollection: LiveData<DataResult<MediaCollection>> get() = _mediaCollection

    private val _navigateToVideosFragment = MutableLiveData<Boolean>()
    val navigateToVideosFragment: LiveData<Boolean> = _navigateToVideosFragment

    init {
        _mediaCollection.value = null
        _navigateToVideosFragment.value = false
        /*
        Il faut appeler cette séquences des méthodes lors d'un changement des params du ws
        Par exemple : depuis un fichier de config, depuis utilisateur via un pop-up pour taper les entrées
         */
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                useCase.saveWebServiceParams(defaultWebServiceParams)
            }

            withContext(Dispatchers.IO) {
                useCase.setOrUpdateWebServiceParams()
            }
        }
    }

    fun getAndSaveVideo() {
        viewModelScope.launch {
            _workState.postValue(WorkState.IN_PROGRESS)
            val mediaCollection = withContext(Dispatchers.IO) {
                useCase.getMediaCollection()
            }
            _workState.postValue(WorkState.DONE)
            _mediaCollection.postValue(mediaCollection)

            Log.d("Hienhien", "Get media done")

            withContext(Dispatchers.IO) {
                if (mediaCollection is DataResult.Success) {
                    val videos = mediaCollection.data.videos
                    useCase.saveVideos(videos)
                    Log.d("Hienhien", "Save videos done : ${videos.size}")
                }
            }

            val videosInDB = withContext(Dispatchers.IO) {
                if (mediaCollection is DataResult.Success) {
                    useCase.findVideos()
                }
            }
            Log.d("Hienhien", "Find videos done")
        }
    }

    fun onBtnAllVideosClicked() {
        _navigateToVideosFragment.value = true
    }

    fun doneNavigateToAllsVideos() {
        _navigateToVideosFragment.value = false
    }
}
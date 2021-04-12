package com.test.videosstories.playvideo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlayerVideoViewModel : ViewModel() {

    private val _urlVideo = MutableLiveData<String>()
    val urlVideo: LiveData<String> get() = _urlVideo

    private val _backToHome = MutableLiveData<Boolean>()
    val backToHome: LiveData<Boolean> get() = _backToHome

    init {
        _backToHome.value = false
    }

    fun setUrl(url: String) {
        _urlVideo.value = url
    }

    fun onBackClicked() {
        _backToHome.value = true
    }

    fun doneBackToHome() {
        // Reinit
        _backToHome.value = false
    }

}

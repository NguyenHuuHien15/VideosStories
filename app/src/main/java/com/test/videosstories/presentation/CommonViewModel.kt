package com.test.videosstories.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

open class CommonViewModel @Inject constructor() : ViewModel() {
    protected val _workState: MutableLiveData<WorkState> = MutableLiveData()
    val workState: LiveData<WorkState> get() = _workState

    init {
        _workState.value = WorkState.NOT_YET
    }
}
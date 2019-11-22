package edu.uw.stm7631.noteworthy.ui.camera

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CameraViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "New camera note here"
    }
    val text: LiveData<String> = _text
}
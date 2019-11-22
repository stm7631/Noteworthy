package edu.uw.stm7631.noteworthy.ui.courses

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CoursesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "New note here"
    }
    val text: LiveData<String> = _text
}
package com.koshake1.pulseapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koshake1.pulseapp.model.repository.NotesRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainViewModel(repository : NotesRepository) : ViewModel() {
    private val notesLiveData = MutableLiveData<DataViewState>()

    init {
        repository.observeNotes()
            .onEach {
                notesLiveData.value = if (it.isEmpty()) DataViewState.EMPTY else DataViewState.Value(it)
            }
            .launchIn(viewModelScope)
    }

    fun observeViewState(): LiveData<DataViewState> = notesLiveData
}
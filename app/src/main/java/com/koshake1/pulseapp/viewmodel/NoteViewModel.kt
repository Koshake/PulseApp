package com.koshake1.pulseapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koshake1.pulseapp.model.data.Note
import com.koshake1.pulseapp.model.repository.NotesRepository
import kotlinx.coroutines.launch

class NoteViewModel(private val notesRepository: NotesRepository, var note: Note?) : ViewModel() {

    fun updateDiasPressure(text: String) {
        note = (note ?: generateNote()).copy(dias_pressure = text)
    }


    fun updateSysPressure(text: String) {
        note = (note ?: generateNote()).copy(sys_pressure = text)
    }

    fun updatePulse(text: String) {
        note = (note ?: generateNote()).copy(pulse = text)
    }

    fun saveNote() {
        viewModelScope.launch {
            val noteValue = note ?: return@launch
            notesRepository.addOrReplaceNote(noteValue)
        }
    }

    fun deleteNote() {
        viewModelScope.launch {
            val noteValue = note ?: return@launch
            notesRepository.deleteNote(noteValue)
        }
    }

    private fun generateNote(): Note {
        return Note()
    }
}
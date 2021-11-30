package com.koshake1.pulseapp.db

import com.koshake1.pulseapp.model.data.Note
import kotlinx.coroutines.flow.Flow

interface DatabaseProvider {
    fun observeAllNotes() : Flow<List<Note>>
    suspend fun addNote(newNote : Note)
    suspend fun deleteNote(note : Note)
}
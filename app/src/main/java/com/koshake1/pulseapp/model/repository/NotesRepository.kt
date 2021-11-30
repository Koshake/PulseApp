package com.koshake1.pulseapp.model.repository

import com.koshake1.pulseapp.model.data.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    fun observeNotes(): Flow<List<Note>>
    suspend fun addOrReplaceNote(newNote: Note)
    suspend fun deleteNote(deletedNote: Note)
}
package com.koshake1.pulseapp.model.repository

import com.koshake1.pulseapp.db.DatabaseProvider
import com.koshake1.pulseapp.model.data.Note
import kotlinx.coroutines.flow.Flow
import java.util.*

class NotesRepositoryImpl(private val provider: DatabaseProvider) : NotesRepository {
    override fun observeNotes(): Flow<List<Note>> =
        provider.observeAllNotes()

    override suspend fun addOrReplaceNote(newNote: Note) {
        provider.addNote(newNote)
    }

    override suspend fun deleteNote(deletedNote: Note) {
        provider.deleteNote(deletedNote)
    }
}
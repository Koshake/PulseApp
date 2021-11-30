package com.koshake1.pulseapp.model.repository

import com.koshake1.pulseapp.model.data.Note
import kotlinx.coroutines.flow.Flow
import java.util.*

class NotesRepositoryImpl : NotesRepository {
    override fun observeNotes(): Flow<List<Note>> {
        TODO("Not yet implemented")
    }

    override suspend fun addOrReplaceNote(newNote: Note) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNote(deletedNote: Note) {
        TODO("Not yet implemented")
    }

    override fun getDate() : String {
        val calendar = GregorianCalendar()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)
        return String.format(Locale.getDefault(),"%02d.%02d.%04d",day, month + 1, year)
    }

    override fun getTime(): String {
        val calendar = GregorianCalendar()
        val hour = calendar.get(Calendar.HOUR)
        val minute = calendar.get(Calendar.MINUTE)
        return String.format(Locale.getDefault(),"%02d:%02d", hour, minute)
    }
}
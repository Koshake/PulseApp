package com.koshake1.pulseapp.db

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.koshake1.pulseapp.model.data.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

private const val NOTES_COLLECTION = "notes"
private const val USERS_COLLECTION = "users"
const val TAG = "FireStoreDatabase"

class FireBaseProvider (
    private val db: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
) : DatabaseProvider {

    private val result = MutableStateFlow<List<Note>?>(null)
    private var subscribedOnDb = false

    override fun observeAllNotes(): Flow<List<Note>> {
        TODO("Not yet implemented")
    }

    override suspend fun addNote(newNote: Note) {

    }
}
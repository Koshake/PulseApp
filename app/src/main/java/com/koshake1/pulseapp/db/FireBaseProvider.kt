package com.koshake1.pulseapp.db

import android.util.Log
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.*
import com.koshake1.pulseapp.model.data.Note
import com.koshake1.pulseapp.viewmodel.DataViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

private const val NOTES_COLLECTION = "notes"
private const val USERS_COLLECTION = "users"
const val TAG = "FireStoreDatabase"

class FireBaseProvider(
    private val db: FirebaseFirestore,
) : DatabaseProvider {

    private val result = MutableStateFlow<List<Note>?>(null)
    private var subscribedOnDb = false

    private val notesReference = db.collection(NOTES_COLLECTION)

    override fun observeAllNotes(): Flow<List<Note>> {
        if (!subscribedOnDb) subscribeForDbChanging()
        return result.filterNotNull()
    }

    override suspend fun addNote(newNote: Note) {
        suspendCoroutine<Unit> { continuation ->
            notesReference.document(newNote.id.toString())
                .set(newNote)
                .addOnSuccessListener {
                    Log.d(TAG, "Note $newNote is saved")
                    continuation.resumeWith(Result.success(Unit))
                }
                .addOnFailureListener {
                    Log.d(TAG, "Error saving note $newNote, message: ${it.message}")
                    continuation.resumeWithException(it)
                }
        }
    }

    override suspend fun deleteNote(note: Note) {
        suspendCancellableCoroutine<Unit> { continuation ->
            notesReference.document(note.id.toString()).delete()
                .addOnSuccessListener {
                    continuation.resumeWith(Result.success(Unit))
                }.addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }
    }

    private fun subscribeForDbChanging() {
        notesReference.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.e(TAG, "Observe note exception:$e")
            } else if (snapshot != null) {
                val notes = mutableListOf<Note>()

                for (doc: QueryDocumentSnapshot in snapshot) {
                    notes.add(doc.toObject(Note::class.java))
                }

                result.value = notes
            }
        }
        subscribedOnDb = true
    }
}
package ru.ershovao.notesapp.data.firebase.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import ru.ershovao.notesapp.data.DatabaseRepository
import ru.ershovao.notesapp.data.firebase.AllNotesLiveData
import ru.ershovao.notesapp.model.Note
import ru.ershovao.notesapp.utils.Constants.Keys.FIREBASE_ID
import ru.ershovao.notesapp.utils.Constants.Keys.SUBTITLE
import ru.ershovao.notesapp.utils.Constants.Keys.TITLE
import ru.ershovao.notesapp.utils.LOGIN
import ru.ershovao.notesapp.utils.PASSWORD

class FirebaseRepository : DatabaseRepository {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val database = Firebase.database.reference.child(auth.currentUser?.uid.toString())
    override val readAll: LiveData<List<Note>> = AllNotesLiveData()

    override suspend fun create(note: Note, onSuccess: () -> Unit) {
        val noteId = database.push().key.toString()
        val mapNotes = hashMapOf<String, Any>()
        mapNotes[FIREBASE_ID] = noteId
        mapNotes[TITLE] = note.title
        mapNotes[SUBTITLE] = note.subTitle
        database.child(noteId)
            .updateChildren(mapNotes)
            .addOnSuccessListener {
                onSuccess()
                Log.d("checkData", "SUCCESS")
            }
            .addOnFailureListener {
                Log.d("checkData", "ERROR: ${it}")
            }
    }

    override suspend fun update(note: Note, onSuccess: () -> Unit) {
        val mapNotes = hashMapOf<String, Any>()
        mapNotes[FIREBASE_ID] = note.firebaseId
        mapNotes[TITLE] = note.title
        mapNotes[SUBTITLE] = note.subTitle
        database.child(note.firebaseId)
            .updateChildren(mapNotes)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                Log.d("checkData", "ERROR: ${it}")
            }
    }

    override suspend fun delete(note: Note, onSuccess: () -> Unit) {
        database.child(note.firebaseId)
            .removeValue()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { Log.d("checkData", "ERROR: ${it}") }
    }

    override fun signOut() {
        auth.signOut()
    }

    override fun connectToDatabase(onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        auth.signInWithEmailAndPassword(LOGIN, PASSWORD)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                auth.createUserWithEmailAndPassword(LOGIN, PASSWORD)
                    .addOnSuccessListener {
                        onSuccess()
                    }
                    .addOnFailureListener {
                        onFailure(it.message.toString())
                    }
            }
    }
}
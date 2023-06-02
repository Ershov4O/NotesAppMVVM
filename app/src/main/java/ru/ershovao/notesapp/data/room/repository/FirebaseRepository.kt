package ru.ershovao.notesapp.data.room.repository

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import ru.ershovao.notesapp.data.DatabaseRepository
import ru.ershovao.notesapp.model.Note
import ru.ershovao.notesapp.utils.LOGIN
import ru.ershovao.notesapp.utils.PASSWORD

class FirebaseRepository : DatabaseRepository {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override val readAll: LiveData<List<Note>>
        get() = TODO("Not yet implemented")

    override suspend fun create(note: Note, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun update(note: Note, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(note: Note, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
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
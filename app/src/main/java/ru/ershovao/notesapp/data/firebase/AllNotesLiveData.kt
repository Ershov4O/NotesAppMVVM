package ru.ershovao.notesapp.data.firebase

import androidx.compose.animation.core.snap
import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import ru.ershovao.notesapp.model.Note

class AllNotesLiveData : LiveData<List<Note>>() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val database = Firebase.database.reference.child(auth.currentUser?.uid.toString())
    private val listener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val notes: MutableList<Note> = mutableListOf()
            snapshot.children.map {
                notes.add(it.getValue(Note::class.java) ?: Note())
            }
            value = notes
        }

        override fun onCancelled(error: DatabaseError) {}
    }

    override fun onActive() {
        super.onActive()
        database.addValueEventListener(listener)
    }

    override fun onInactive() {
        super.onInactive()
        database.removeEventListener(listener)
    }
}
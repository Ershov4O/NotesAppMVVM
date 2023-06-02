package ru.ershovao.notesapp.data.room.repository

import androidx.lifecycle.LiveData
import ru.ershovao.notesapp.data.DatabaseRepository
import ru.ershovao.notesapp.data.room.dao.NoteRoomDao
import ru.ershovao.notesapp.model.Note

class RoomRepository(private val noteRoomDao: NoteRoomDao) : DatabaseRepository {
    override val readAll: LiveData<List<Note>> = noteRoomDao.getAllNotes()

    override suspend fun create(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.addNote(note)
        onSuccess()
    }

    override suspend fun update(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.updateNote(note)
        onSuccess()
    }

    override suspend fun delete(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.deleteNote(note)
        onSuccess()
    }
}
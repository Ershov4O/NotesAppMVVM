package ru.ershovao.notesapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.ershovao.notesapp.data.room.AppRoomDatabase
import ru.ershovao.notesapp.data.room.repository.FirebaseRepository
import ru.ershovao.notesapp.data.room.repository.RoomRepository
import ru.ershovao.notesapp.model.Note
import ru.ershovao.notesapp.utils.REPOSITORY
import ru.ershovao.notesapp.utils.TYPE_FIREBASE
import ru.ershovao.notesapp.utils.TYPE_ROOM

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application
    fun initDB(type: String, onSuccess: () -> Unit) {
        when (type) {
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(context = context).getRoomDao()
                REPOSITORY = RoomRepository(dao)
                onSuccess()
            }

            TYPE_FIREBASE -> {
                REPOSITORY = FirebaseRepository()
                REPOSITORY.connectToDatabase(
                    onSuccess = {
                        onSuccess()
                    },
                    onFailure = {
                        Log.d("dataBaseInit", "ERROR: $it")
                    }
                )
            }

            else -> {}
        }
        Log.d("dataBaseInit", "MainViewModel initDB with $type")
    }

    fun addNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.create(note) {
                launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun updateNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.update(note) {
                launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun deleteNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.delete(note) {
                launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun readAllNotes() = REPOSITORY.readAll
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown viewModel class")
    }
}
package ru.ershovao.notesapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.ershovao.notesapp.data.room.AppRoomDatabase
import ru.ershovao.notesapp.data.room.repository.RoomRepository
import ru.ershovao.notesapp.utils.REPOSITORY
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

            else -> {}
        }
        Log.d("dataBaseInit", "MainViewModel initDB with $type")
    }
}

class MainViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown viewModel class")
    }
}